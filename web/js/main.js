
$(document).on('click', '.newSubsFile', function (e) {
    console.log('click .newSubsFile');
    var fileId = $(this).attr('fileId');
    
    $.post("EditNewSubtitles", {fileId: fileId}, function(e){
        
    });
});

//$(document).on('click', '.subOn', function (e) {
//    console.log('click .subOn');
//    var subsId = $(this).attr('id');
//    
//    $.post("EditOldSubtitles", {subsId: subsId}, function(e){
//        
//    });
//});