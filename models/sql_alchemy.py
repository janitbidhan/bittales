import os 
from sqlalchemy import create_engine, text 
DATABASE_URL = "cockroachdb://bittales-user:zG8FX7c8UCqTXmEktR9zlg@db-bittales-10099.7tt.cockroachlabs.cloud:26257/defaultdb?verify-full" 
# engine = create_engine(DATABASE_URL) 
# conn = engine.connect() 
# res = conn.execute(text("SELECT now()")).fetchall() 
# print(res)


from flask import Flask, render_template, request, redirect, url_for, flash
from flask_bootstrap import Bootstrap
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user
app = Flask(__name__)
Bootstrap(app)

app.config['SQLALCHEMY_DATABASE_URI'] = DATABASE_URL

from flask_sqlalchemy import SQLAlchemy
db = SQLAlchemy(app)




class stories(UserMixin, db.Model):
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(40))
	genre = db.Column(db.String)
	audiolink = db.Column(db.String,)
	usercreated = db.Column(db.Boolean)
	linkbook = db.Column(db.String)
	def __init__(self, id, name, genre, audiolink, usercreated, linkbook):
		super().__init__()
		self.id = id
		self.name = name
		self.genre = genre
		self.audiolink  =audiolink
		self.usercreated = usercreated
		self.linkbook = linkbook

#user = stories.query.filter_by(genre='children').first()