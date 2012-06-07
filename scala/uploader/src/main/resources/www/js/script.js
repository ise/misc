var dropbox = $('#dropbox');
var message = $('.message', dropbox);

dropbox.filedrop({
    paramname: 'file',
    url: '/upload',
    uploadFinished: function(i, file, response) {
        console.log(i);
        console.log(file);
        console.log(response);
        $('box').addClass('done');
    },
    beforeEach: function(file) {
        console.log(file);
    },
    uploadStarted: function(i, file, len) {
        console.log(i);
        console.log(file);
        console.log(len);
    }
});
