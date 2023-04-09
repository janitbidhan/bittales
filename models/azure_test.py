import os
import azure.cognitiveservices.speech as speechsdk

SPEECH_KEY="a368804b67e9492ebd426784e1d332fe"
SPEECH_REGION="eastus"

# This example requires environment variables named "SPEECH_KEY" and "SPEECH_REGION"
speech_config = speechsdk.SpeechConfig(subscription=SPEECH_KEY, region=SPEECH_REGION)
audio_config = speechsdk.audio.AudioOutputConfig(use_default_speaker=True)

# The language of the voice that speaks.
speech_config.speech_synthesis_voice_name='en-US-JennyNeural'

speech_synthesizer = speechsdk.SpeechSynthesizer(speech_config=speech_config, audio_config=audio_config)

# Get text from the console and synthesize to the default speaker.
#print("Enter some text that you want to speak >")
def getWavFile(text = "Hello there, Sorry I couldn't understand the last part. Could you clarify it again", output_file_name = 'dummy_output.wav'):
	#text = input()
	print('Text to Wav: ', text)
	print('File name: ', output_file_name)
	speech_synthesis_result = speech_synthesizer.speak_text_async(text).get()
	stream = speechsdk.AudioDataStream(speech_synthesis_result)
	stream.save_to_wav_file(output_file_name)

def recognize_from_microphone(wav_address = "./kasbdjashd.wav"):
    # This example requires environment variables named "SPEECH_KEY" and "SPEECH_REGION"
    print('Converting audio to text ', wav_address)
    speech_config = speechsdk.SpeechConfig(subscription=SPEECH_KEY, region=SPEECH_REGION)
    speech_config.speech_recognition_language="en-US"
    audio_config = speechsdk.audio.AudioConfig(filename=wav_address)
    speech_recognizer = speechsdk.SpeechRecognizer(speech_config=speech_config, audio_config=audio_config)
    #print("Speak into your microphone.")
    speech_recognition_result = speech_recognizer.recognize_once_async().get()
    if speech_recognition_result.reason == speechsdk.ResultReason.RecognizedSpeech:
        print("Recognized: {}".format(speech_recognition_result.text))
        return speech_recognition_result.text
    return "Sorry, couldn't understand that"