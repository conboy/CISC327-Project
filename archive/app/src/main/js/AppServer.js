var http = require('http');
var fs = require('fs');

// regexs for different requests
const getRoot = new RegExp("^/$")
const getUpdateProfile = new RegExp("^/updateUserProfile")

let handleRequest = (request, response) => {
    response.writeHead(200, {
        'Content-Type': 'text/html'
    });
    var url = request.url;
    console.log(url)
    switch(true) { // Parse url. Tests regexs against url
            case getRoot.test(url):
                    var page = 'qbnb/login.html'
                    break;
            case getUpdateProfile.test(url):
                    var page = './qbnb/updateUserProfile.html';
                    break;
            default:
                    var page = './qbnb/index.html'
    }
    fs.readFile(page, null, function (error, data) { // change view
        if (error) {
            response.writeHead(404);
            response.write('Whoops! Page not found!');
        } else {
            response.write(data);
        }
        response.end();
    });
};
http.createServer(handleRequest).listen(8080); 
