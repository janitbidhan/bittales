import torch
from transformers import Speech2TextProcessor, Speech2TextForConditionalGeneration
from datasets import load_dataset
from chatGPT import get_story
from azure_test import *
from upload_gdrive import *
import os 
from sqlalchemy import create_engine, text 
DATABASE_URL = "cockroachdb://bittales-user:zG8FX7c8UCqTXmEktR9zlg@db-bittales-10099.7tt.cockroachlabs.cloud:26257/defaultdb?verify-full" 
creds = service_account.Credentials.from_service_account_file('g_api_keys.json')


from flask import Flask, render_template, request, redirect, url_for, flash
from flask_bootstrap import Bootstrap
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user
app = Flask(__name__)
Bootstrap(app)

app.config['SQLALCHEMY_DATABASE_URI'] = DATABASE_URL

from flask_sqlalchemy import SQLAlchemy
db = SQLAlchemy(app)


'''
new_user_data = UserData(username=current_user.username, filedone=threadFile, threadTime=timeSpan, emailsTime=json.dumps(allTurnTime))
db.session.add(new_user_data)
db.session.commit()



'''
def make_change_db(identifier, target_col, name=None, genre=None, audiolink=None, usercreated=None, linkbook=None, gid=None):
    if(identifier is not None):#it means we are querying for an existing story
        record = stories.query.filter_by(identifier=target_col).first()#most probably it will be name = "story name"
        if(record is None):
            identifier = None
    #id, name, genre, audiolink, usercreated, linkbook, gid
    if(identifier is None):# we have a new story so store it
        with app.app_context():
	        new_record = stories(id = 123, name = name , genre = genre, audiolink = audiolink, usercreated = usercreated, linkbook = linkbook, gid = gid)
	        db.session.add(new_record)
	        db.session.commit()


def get_storyM():
    text = get_story(genre = "prince and princess", length=10)["choices"][0]["text"]
    print('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>', text)
    story, story_name = text.split('Story name is:')
    story_name = story_name.replace(" ", "_").lower().strip()
    print("Generated Story: ", text)
    #getWavFile(text = text, output_file_name=file_name)
    #print('Generated story: ', text)
    getWavFile(text = text, output_file_name = f'./audio/{story_name}wav')
    #gid_ = upload_to_drive(creds, f'./audio/{story_name}wav')
    return story, story_name


# engine = create_engine(DATABASE_URL) 
# conn = engine.connect() 
# res = conn.execute(text("SELECT now()")).fetchall() 
# print(res)

class stories(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(40))
    genre = db.Column(db.String)
    audiolink = db.Column(db.String,)
    usercreated = db.Column(db.Boolean)
    linkbook = db.Column(db.String)
    gid = db.Column(db.String)
    def __init__(self, id, name, genre, audiolink, usercreated, linkbook, gid):
        super().__init__()
        self.id = id
        self.name = name
        self.genre = genre
        self.audiolink  = audiolink#audio address
        self.usercreated = usercreated
        self.linkbook = linkbook#text book address
        self.gid = gid

def queryDB(story_name):
    stories = stories.query.filter_by(name = story_name).first()
    if(stories is not None):
        return stories[0].audiolink
    else:
        return -1

def load_storyM(file_name):
    translation = recognize_from_microphone(wav_address = file_name)
    print(translation)
    return translation



# file_name = "my_test.wav"
# get_storyM("my_test.wav")
# load_storyM(f'./audio/{file_name}')


#AIzaSyBWx3FU7t3Rmp54S6MD7fm6k370YTX8kTU

def start(story_name, genre=None):
    if(story_name==None or story_name.strip()=="" or story_name==-1):
        #we have to create a new story
        story, story_name = get_storyM()
        text = load_storyM(f'./audio/{story_name}wav')    
        f = open(f'./audio/{story_name}txt', 'w')
        _ = f.write(text + '\n')
        f.close()    
        gid = upload_to_drive(creds,f'./audio/{story_name}txt')
        make_change_db(None, None, name=story_name, genre=genre, audiolink=f'./audio/{story_name}wav', usercreated=True, linkbook=f'./audio/{story_name}txt', gid=gid)
    else:#we have an existing story to pick from 
        try:
            text = queryDB(story_name)
            assert text !=-1
        except:
            text = -1
    return text



#start("")



    