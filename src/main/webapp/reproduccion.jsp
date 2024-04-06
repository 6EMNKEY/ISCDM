<%-- 
    Document   : reproduccion
    Created on : 4 abr 2024, 10:55:02
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Videos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
        }
        #search-container {
            text-align: center;
            margin-top: 50px;
        }
        #search-container input[type="text"] {
            padding: 10px;
            width: 300px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        #search-container input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        #result-container {
            margin-top: 20px;
            text-align: center;
        }
        #result-container iframe {
            width: 800px;
            height: 450px;
        }
    </style>
</head>
<body>

<header>
    <h1>Search Videos</h1>
</header>

<div id="search-container">
    <form id="search-form">
        <input type="text" id="search-input" placeholder="Enter video keywords...">
        <input type="submit" value="Search">
    </form>
</div>

<div id="result-container">
    <iframe id="video-frame" src="" frameborder="0" allowfullscreen></iframe>
</div>
</body>
</