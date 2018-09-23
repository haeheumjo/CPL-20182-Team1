var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
    console.log("get success");
});

let year,month,date,hour,minute,locationType,locationNmae,latitude,longitude;

io.on('connection', function(socket){
    console.log('user connection.');

    socket.on('sendScheduleInfo',function(data){
        
        year = data.date.split('/')[0];
        month = data.date.split('/')[1];
        date = data.date.split('/')[2];
        hour = data.time.split(':')[0];
        minute = data.time.split(':')[1];
        locationType = data.locationType;
        locationNmae = data.locationNmae;
        latitude = data.latitude;
        longitude = data.longitude;

        console.log('date: ',year);
    })

    socket.on('sendLocation',function(data){
        socket.emit('sendLocation',db);
    })

    socket.on('disconnect',function(){
        console.log('disconnection');
    })

});

http.listen(3000, function(){ 
    console.log('port number is 3000');
});