# Import the Speech-to-Text client library
from google.cloud import speech

# Instantiates a client
client = speech.SpeechClient()

# The name of the audio file to transcribe
gcs_uri = "gs://bitcamp_salokr/audio-files/audio_after.wav"

def transcribe_speech():
  audio = speech.RecognitionAudio(uri=gcs_uri)

  config = speech.RecognitionConfig(
    encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
    sample_rate_hertz=22050,
    language_code="en-AU",
    model="default",
    audio_channel_count=1,
    enable_word_time_offsets=True,
  )

  # Detects speech in the audio file
  operation = client.long_running_recognize(config=config, audio=audio)

  print("Waiting for operation to complete...")
  response = operation.result(timeout=90)

  for result in response.results:
    print("Transcript: {}".format(result.alternatives[0].transcript))

transcribe_speech()