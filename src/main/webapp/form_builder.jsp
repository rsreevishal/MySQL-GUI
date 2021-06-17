<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Form Builder</title>
 <!--Jquery-->
    <script
      src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
      integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!--Bootstrap-->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
      integrity="undefined"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
      integrity="undefined"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
    <style>
      .close {
        cursor: pointer;
        position: absolute;
        top: 10%;
        right: 0%;
        padding: 8px;
        transform: translate(0%, -50%);
        color: red;
      }
    </style>
  </head>
</head>
<body>
<div class="container py-5 text-center">
      <h1 class="display-4">Form Builder</h1>
	  <a href="/mysqlgui/Dashboard" class="btn btn-primary" role="button">Back</a>
	  <hr class="my-4"/>
      <div class="row text-center">
        <div class="col-lg-2">
          <h3>Components</h3>
        </div>
        <div class="col-lg-10">
          <h3>Builder</h3>
        </div>
      </div>
      <div class="row text-center">
        <div class="col-lg-2 border border-primary">
          <ul class="list-group" id="components">
            <li class="list-group-item draggable" name="TEXT">Text</li>
            <li class="list-group-item draggable" name="NUMBER">Number</li>
            <li class="list-group-item draggable" name="RADIO">Radio</li>
            <li class="list-group-item draggable" name="CHECKBOX">Checkbox</li>
            <li class="list-group-item draggable" name="LINK">Link</li>
          </ul>
        </div>
        <div class="col-lg-10 border border-success list-group droppable sortable" id="builder">
        </div>
      </div>
      <form id="form-builder">
        <div class="row py-3 text-center">
            <div class="col-lg-8">
              <input id="form_name" class="form-control" type="text" name="form_name" required placeholder="Form name"/>
            </div>
            <div class="col-lg-4">
              <button id="create_form" class="btn btn-success">Create Form</button>
            </div>
        </div>
      </form>
    </div>

  </body>
  <script>
    $(".draggable").draggable({
      helper: "clone",
    });
    $(".sortable").sortable();
    $(".droppable").droppable({
      accept: ".draggable",
      drop: function (event, ui) {
        var droppable = $(ui.draggable).clone();
        $(this).append(getElement(droppable.attr("name")));
        $(".close").each(function () {
          $(this).click(function (event) {
            $(this).parent().remove();
          });
        });
      },
    });

    $("#create_form").click(function(event) {
      event.preventDefault();
      if($("#form-builder").valid()) {
        var valid = true;
        var inputs = [];
        if($("#builder li").length == 0) {
          valid = false;
        }
        $("#builder li").each(function(i) {
          switch($(this).attr("name")) {
            case "TEXT": {
              valid = $(this).find('form').valid();
              var name = $(this).find('input[name="inp_name"]').val();
              var minl = $(this).find('input[name="inp_minl"]').val()
              var maxl = $(this).find('input[name="inp_maxl"]').val()
              var result = `${name} TEXT ('${minl}','${maxl}')`;
              inputs.push(result);
              break;
            }
            case "NUMBER": {
              valid = $(this).find('form').valid();
              var name = $(this).find('input[name="inp_name"]').val();
              var min = $(this).find('input[name="inp_min"]').val()
              var max = $(this).find('input[name="inp_max"]').val()
              var result = `${name} NUMBER ('${min}','${max}')`;
              inputs.push(result);
              break;
            }
            case "RADIO": {
              valid = $(this).find('form').valid();
              var name = $(this).find('input[name="inp_name"]').val();
              var values = $(this).find('input[name="inp_values"]').val()
              var filtered = []
              values.split(",").forEach(function(e) {
                filtered.push(`'${e}'`);
              });
              var result = `${name} RADIO (${filtered.join()})`;
              inputs.push(result);
              break;
            }
            case "CHECKBOX": {
              valid = $(this).find('form').valid();
              var name = $(this).find('input[name="inp_name"]').val();
              var values = $(this).find('input[name="inp_values"]').val()
              var filtered = []
              values.split(",").forEach(function(e) {
                filtered.push(`'${e}'`);
              });
              var result = `${name} CHECKBOX (${filtered.join()})`;
              inputs.push(result);
              break;
            }
            case "LINK": {
              valid = $(this).find('form').valid();
              var name = $(this).find('input[name="inp_name"]').val();
              var table = $(this).find('input[name="inp_table"]').val()
              var col = $(this).find('input[name="inp_col"]').val()
              var result = `${name} LINK ('${table}','${col}')`;
              inputs.push(result);
              break;
            }
          }
        });
        if(valid) {
          var form_name = $("#form_name").val();
          var result = `CREATE FORM ${form_name} [\n${inputs.join('\n')}\n]`;
          console.log(result);
          $.ajax({
              type: "POST",
              url: "/mysqlgui/Crudql",
              data: `query=${result}&tab=tab2`,
              success: function(data)
              {
            	  alert("Successfully created form");
              },
              error: function(data) {
            	  alert("Failed to create form");
              }
           });
        } else {
          alert("Please fill all the data!");
        }
      }
    });
    
    function getElement(element) {
      switch(element) {
        case "TEXT": {
          return $(`
          <li name="TEXT" class="list-group-item sortable">
          <span class='close'>X</span>
          <label>Text</label>
          <form>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_name" class='form-control' type="text" placeholder="Name" required />
              </div>
            </div>
            <div class="row">
              <div class="col-lg-6">
                <input name="inp_minl" class='form-control' type="number" placeholder="min length" required />
              </div>
              <div class="col-lg-6">
                <input name="inp_maxl" class='form-control' type="number" placeholder="max length" required />
              </div>
            </div>
          </div>
          </form>
          </li>
          `);
        }
        case "NUMBER": {
          return $(`
          <li name="NUMBER" class="list-group-item sortable">
          <span class='close'>X</span>
          <label>Number</label>
          <form>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_name" class='form-control' type="text" placeholder="Name" required/>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-6">
                <input name="inp_min" class='form-control' type="number" placeholder="min" required/>
              </div>
              <div class="col-lg-6">
                <input name="inp_max" class='form-control' type="number" placeholder="max" required/>
              </div>
            </div>
          </div>
          </form>
          </li>
          `);
        }
        case "RADIO": {
          return $(`
          <li name="RADIO" class="list-group-item sortable">
          <span class='close'>X</span>
          <label>Radio</label>
          <form>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_name" class='form-control' type="text" placeholder="Name" required />
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_values" class='form-control' type="text" placeholder="Values(seperated by comma ,)" required />
              </div>
            </div>
          </div>
          </form>
          </li>
          `);
        }
        case "CHECKBOX": {
          return $(`
          <li name="CHECKBOX" class="list-group-item sortable">
          <span class='close'>X</span>
          <label>Checkbox</label>
          <form>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_name" class='form-control' type="text" placeholder="Name" required />
              </div>
            </div>
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_values" class='form-control' type="text" placeholder="Values(seperated by comma ,)" required />
              </div>
            </div>
          </div>
          </form>
          </li>
          `);
        }
        case "LINK": {
          return $(`
          <li name="LINK" class="list-group-item sortable">
          <span class='close'>X</span>
          <label>Link</label>
          <form>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <input name="inp_name" class='form-control' type="text" placeholder="Name" required />
              </div>
            </div>
            <div class="row">
              <div class="col-lg-6">
                <input name="inp_table" class='form-control' type="text" placeholder="Table" required />
              </div>
              <div class="col-lg-6">
                <input name="inp_col" class='form-control' type="text" placeholder="Column" required />
              </div>
            </div>
          </div>
          </form>
          </li>
          `);
        }
        default: {
          return $("<p>Unknown element</p>");
        }
      }
    }
  </script>