from flask_mail import Mail, Message
import datetime
from dateutil.relativedelta import relativedelta
from dateutil import parser
import copy
from cProfile import label
from distutils.command.build import build
from email import message
from enum import unique
from fileinput import filename
from inspect import currentframe
import json
import nltk
from wsgiref.validate import validator
from tkinter.tix import Tree
from sqlite3 import Timestamp
import os
import sys
import re
import numpy as np
from distutils.log import debug
from flask import Flask, render_template, request, redirect, url_for, flash
from flask_sqlalchemy import SQLAlchemy
from flask_wtf import FlaskForm
from wtforms import PasswordField, BooleanField, EmailField
from wtforms.validators import InputRequired, Email, Length
from flask_bootstrap import Bootstrap
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user
from itsdangerous import URLSafeTimedSerializer



import ast
app = Flask(__name__)
Bootstrap(app)
app.config['SECRET_KEY'] = "YaoLab"

@app.route('/homepage', methods=['GET', 'POST'])
def homepage():
    return render_template("dash.html")

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port="1234", threaded=True)

