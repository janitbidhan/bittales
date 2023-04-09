output_file='/Users/username/micaudio.wav'

import pyaudio, signal, sys, os, requests, wave
pa = pyaudio.PyAudio()
import azure.cognitiveservices.speech as speechsdk

def vocrec_callback(in_data, frame_count, time_info, status):
    global voc_data
    voc_data['frames'].append(in_data)
    return (in_data, pyaudio.paContinue)

def vocrec_start():
    global voc_stream
    global voc_data
    voc_data = {
        'channels':1 if sys.platform == 'darwin' else 2,
        'rate':44100,
        'width':pa.get_sample_size(pyaudio.paInt16),
        'format':pyaudio.paInt16,
        'frames':[]
    }
    voc_stream = pa.open(format=voc_data['format'],
                    channels=voc_data['channels'],
                    rate=voc_data['rate'],
                    input=True,
                    output=False,
                    stream_callback=vocrec_callback)
    
def vocrec_stop():
    voc_stream.close()

def vocrec_write():
    with wave.open(output_file, 'wb') as wave_file:
        wave_file.setnchannels(voc_data['channels'])
        wave_file.setsampwidth(voc_data['width'])
        wave_file.setframerate(voc_data['rate'])
        wave_file.writeframes(b''.join(voc_data['frames']))

class SIGINT_handler():
    def __init__(self):
        self.SIGINT = False
    def signal_handler(self, signal, frame):
        self.SIGINT = True
        print('You pressed Ctrl+C!')
        vocrec_stop()
        quit()

def init_azure():
    global speech_recognizer
    #  ——— check azure keys
    my_speech_key = "a368804b67e9492ebd426784e1d332fe"
    if my_speech_key is None:
        error_and_quit("Error: No Azure Key.")
    my_speech_region = "eastus"
    if my_speech_region is None:
        error_and_quit("Error: No Azure Region.")
    _headers = {
        'Ocp-Apim-Subscription-Key': my_speech_key,
        'Content-type': 'application/x-www-form-urlencoded',
        # 'Content-Length': '0',
    }
    _URL = f"https://{my_speech_region}.api.cognitive.microsoft.com/sts/v1.0/issueToken"
    _response = requests.post(_URL,headers=_headers)
    if not "200" in str(_response):
        error_and_quit("Error: Wrong Azure Key Or Region.")
    #  ——— keys correct. continue
    speech_config = speechsdk.SpeechConfig(subscription=os.environ.get('SPEECH_KEY'),
                                           region=os.environ.get('SPEECH_REGION'))
    audio_config_stt = speechsdk.audio.AudioConfig(use_default_microphone=True)
    speech_config.set_property(speechsdk.PropertyId.SpeechServiceResponse_RequestSentenceBoundary, 'true')
    #  ——— disable profanity filter:
    speech_config.set_property(speechsdk.PropertyId.SpeechServiceResponse_ProfanityOption, "2")
    speech_config.speech_recognition_language="en-US"
    speech_recognizer = speechsdk.SpeechRecognizer(
        speech_config=speech_config,
        audio_config=audio_config_stt)

def error_and_quit(_error):
     print(_error)
     quit()

def recognize_speech ():
    vocrec_start()
    print("Say something: ")
    speech_recognition_result = speech_recognizer.recognize_once_async().get()
    print("Recording done.")
    vocrec_stop()
    vocrec_write()
    quit()

handler = SIGINT_handler()
signal.signal(signal.SIGINT, handler.signal_handler)

init_azure()
recognize_speech()