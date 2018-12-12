$(document).ready(function () {
    getData();
});

var numberGetData = 0;

var author = "Author";
var strDelete = "Delete";
var strChange = "Change";

function getData() {
    if (numberGetData == 1) getData1();
    if (numberGetData == 2) getData2();
}

function getData1() {
    $.getJSON("/recipes/", function (data, textStatus, jqXHR) {
        var out = "<tr><th width='10%' style='text-align: center'>" + author + "</th><th width='10%' style='text-align: center'>" + dish + "</th><th width='80%'>" + recipe + "</th></tr>";
        for (key in data) { 
        	
        	
            out += ("<tr><td style='text-align: center'>" + data[key].fullName + "</td><td style='text-align: center'>" + data[key].nameOfTheDish + "</td><td>" + data[key].recipe + "</td>");
        };

        $(".recipes").html(out);
    })
}

var arrayId = [];

function getData2() {
    $.getJSON("/recipes/", function (data, textStatus, jqXHR) {
        var out = "<tr><th width='10%' style='text-align: center'>" + author + "</th><th width='10%' style='text-align: center'>" + dish + "</th><th width='80%'>" + recipe + "</th></tr>"; 
        arrayId = [];
        for (key in data) { 
            arrayId.push(data[key].id);
            out += ("<tr><td style='text-align: center'>" + data[key].fullName + "</td><td style='text-align: center'>" + data[key].nameOfTheDish + "</td><td>" + data[key].recipe + "</td>" +
                "<td><button class='delete' value='" + key + "'>" + strDelete + "</button></td>" +
                "<td><button class='change' value='" + key + "'>" + strChange + "</button></td></tr>>");
        };
        $(".recipes").html(out);
    })
}

$(document).on('click', '.delete', function (data) {
    var iterat = ($(this).val());
    $.getJSON("/recipes/", function (data, textStatus, jqXHR) {

        $.ajax({
            url: "/recipes/" + arrayId[iterat],
            type: 'DELETE',
            success: function (result) {
                
            }
        });
        getData();
    })
});


/*$( document ).on('click', '.delete', function(data) {
    $.ajax({
        url: 'http://localhost:8080/recipes/'+($(this).val()),
        type: 'DELETE',
        success: function(result) {
            getData();
        }
    });
});*/

$(document).on('submit', '#send', function (data) {
    data.preventDefault();
    var message = "{\"fullName\":\"" + $('#FullName').val() + "\",\"nameOfTheDish\":\"" + $('#NameOfTheDish').val() + "\",\"recipe\":\"" + $('#Recipe').val() + "\"}";
    $.ajax({
        type: 'POST',
        url: "/recipes/",
        data: message,
        contentType: "application/json; charset=utf-8",
        success: function (d) {
            console.log(d.message);
            getData();
        }
    })

    location.href = "#";
    $('#FullName').val("");
    $('#NameOfTheDish').val("");
    $('#Recipe').val("");
})

var putVal = "";

$(document).on('click', '.change', function (data) {
    var $valButton = $(this).val();
    putVal = $valButton;
    location.href = "#change";
    $.getJSON("/recipes/", function (data, textStatus, jqXHR) {
        $('#Name').val(data[$valButton].fullName);
        $('#Dish').val(data[$valButton].nameOfTheDish);
        $('#Recip').val(data[$valButton].recipe);
    });
});

$(document).on('submit', '#recipeChange', function (data) {
    data.preventDefault();
    var message = "{\"fullName\":\"" + $('#Name').val() + "\",\"nameOfTheDish\":\"" + $('#Dish').val() + "\",\"recipe\":\"" + $('#Recip').val() + "\"}";
    var q = putVal;
    $.getJSON("/recipes/", function (dat, textStatus, jqXHR) {
        $.ajax({
            type: 'PUT',
            url: "/recipes/" + arrayId[q],
            data: message,
            contentType: "application/json; charset=utf-8",
            success: function (d) {
                console.log(d.message);
                getData();
            }
        })
    })

    location.href = "#";
    $('#Name').val("");
    $('#Dish').val("");
    $('#Recip').val("");
})