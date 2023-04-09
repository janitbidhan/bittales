from fairseq.checkpoint_utils import load_model_ensemble_and_task_from_hf_hub
from fairseq.models.text_to_speech.hub_interface import TTSHubInterface
#import IPython.display as ipd
import soundfile as sf
from chatGPT import get_story

from azure_test import *

def get_story_text2speech(task, genre, length):
    text = get_story(genre = "prince and princess", length=10)["choices"][0]["text"].replace('\n', '')
    return text
#sf.write('./audio_after.wav', wav, rate, "PCM_16")
