import openai
from time import sleep
import backoff
from openai.error import RateLimitError

my_api_key = "sk----"

openai.api_key = my_api_key

@backoff.on_exception(backoff.expo, RateLimitError)
def completion_with_backoff(prompt):
    got_res = False
    res = {"choices": []}
    try_time  = 0
    while not got_res and try_time<100:
        try_time += 1
        try:
            res = openai.Completion.create(model="text-davinci-003", prompt=prompt, temperature=0.7, max_tokens=256, top_p=1, frequency_penalty=0, presence_penalty=0, stop=["\n\nEND"], n=1)
            got_res = True
        except RateLimitError:
            sleep(3)
        except Exception:
            sleep(3)
    return res


def get_story(genre = "prince and princess", length=100):
    #genre = "prince and princess"
    #length = 10
    my_test_prompt = f"Can you generate a story for an infant about {genre}, the story should be exciting for a child, which should be {length} words long, should make the child cozy. Suggest me a name for the story at the end with the message \"story name is: \".\n\n"
    return completion_with_backoff(my_test_prompt)


#story = get_story()["choices"][0]["text"].replace('\n', '')
#print(story)
