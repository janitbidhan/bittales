from google.oauth2 import service_account
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError
import io
import os
from googleapiclient.http import MediaIoBaseDownload

# set the path to your credentials JSON file




def upload_to_drive(creds, file_path='./audio/the_royal_adventure.txt'):
    # create an instance of the Drive API
    drive_service = build('drive', 'v3', credentials=creds)
    # set the path to the file you want to upload
    #file_path = './audio/the_royal_adventure.txt'
    # create a new file in Google Drive
    file_metadata = {'name': os.path.basename(file_path)}
    file = drive_service.files().create(body=file_metadata, media_body=file_path, fields='id').execute()
    # print the file ID of the uploaded file
    print(f'File ID: {file.get("id")}')
    return file.get("id")


def download_from_drive(creds, file_id):
    # create an instance of the Drive API
    drive_service = build('drive', 'v3', credentials=creds)
    # set the file ID of the file you want to download
    #file_id = '1ARxi2VKN2P48IiRtRtRCZMVlKy1xYK2j'
    # download the file from Google Drive
    request = drive_service.files().get_media(fileId=file_id)
    file = io.BytesIO()
    downloader = MediaIoBaseDownload(file, request)
    done = False
    while done is False:
        status, done = downloader.next_chunk()
        print(f'Download progress: {int(status.progress() * 100)}.')
    # save the downloaded file to disk
    file.seek(0)
    with open('downloaded_file.txt', 'wb') as f:
        f.write(file.read())


#download_from_drive("")