# Spring Boot Mongodb File Storage Service

Structured MongoDB API with Spring for file/folder metadata hierachy

Use Redis for Caching to improve performance

## Design

<img src="https://lucid.app/publicSegments/view/8c2cc88c-0698-4ca3-99a7-d3ca31ac03ac/image.png" width="600" height="400">

## Environment Variables

`MONGO_URI`:MongoDB connection string

`MONGODB_DATABASE`:MongoDB database name

`REDIS_HOST`:Redis host name

`REDIS_PORT`:Redis port

## API

`GET /folder/{folderId}`: Get folder by id

`POST /folder`: Create initial folder for user (root folder) with no parent folder

`POST /folder/{folderId}`: Create new folder under parent folder which specified by folderId

`PUT /folder/{folderId}`: Update folder name

`DELETE /folder/{folderId}`: Delete folder by id (cascade delete on next GET)

`POST /file/{folderId}`: Add file to folder which specified by folderId

`DELETE /file/{fileId}`: Delete file by id (cascade delete on next GET)
