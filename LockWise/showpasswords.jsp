<%@page import="com.lockwise.services.util"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" type="image/png" href="static/keylogo.png"/>
    <title>LockWise</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
  
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="navstyle.css">

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    
    <style>
    .bottom-right 
    {
        position: relative;
        bottom: 0;
        right: 0;
        margin-right: 35px;
        margin-bottom: 30px;
    }

.social-button {
  display: inline-block;
  border-radius: 50%;
  background-color: #8c8c8c;
  background-color: rgba(140,140,140,0.75);
  color: #fff;
  text-align: center;
  font-size: 2em;
  border: 0 none;
  padding: 0;
  text-align: center;
  width: 2em;
  height: 2em;
  line-height: 2em;
  position: relative;
  transition: all 0.25s ease-in-out;
}
.social-button:hover {
  background-color: #FF416C;
  color: #fff;
}
.social-button:hover:before {
  -webkit-animation: social-button-beat 1.5s ease-out infinite;
          animation: social-button-beat 1.5s ease-out infinite;
}
.social-button:hover:after {
  -webkit-animation: social-button-beat 1.5s ease-out 0.4s infinite;
          animation: social-button-beat 1.5s ease-out 0.4s infinite;
}
.social-button:before,
.social-button:after {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  background-color: #e44848;
  width: 100%;
  height: 100%;
  border-radius: 50%;
  opacity: 0;
  transition: all 0.25s ease-in-out;
}
@-webkit-keyframes social-button-beat {
  0% {
    opacity: 0.8;
    transform: scale(1);
  }
  70% {
    opacity: 0;
    transform: scale(1.5);
  }
  100% {
    opacity: 0;
  }
}
@keyframes social-button-beat {
  0% {
    opacity: 0.8;
    transform: scale(1);
  }
  70% {
    opacity: 0;
    transform: scale(1.5);
  }
  100% {
    opacity: 0;
  }
}

.has-icon .form-control {
    padding-left: 2.375rem;
}

.has-icon .form-control-feedback {
    position: absolute;
    z-index: 2;
    display: block;
    width: 1.5rem;
    height: 1.2rem;
    margin: 8px 2px 0px 5px;
    line-height: 2rem;
    text-align: center;
    pointer-events: none;
    color: #aaa;
}

body {
    height: 100%;
}

.bg-img {
  background-image: url('static/padlock.jpg');
  height:85%;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
.glow {
  display: block;
  box-shadow: 0 0 2px #fff, 0 0 10px #fff, 0 0 20px #FF4B2B, 0 0 30px #FF4B2B,
    0 0 40px #FF4B2B, 0 0 50px #FF4B2B;
  -webkit-animation: blink 0.7s infinite alternate;
  animation: blink 0.7s infinite alternate;
}
@-webkit-keyframes blink {
  100% {
    box-shadow: 0 0 3px #fff, 0 0 10px #fff, 0 0 20px #fff, 0 0 40px #FF4B2B,
      0 0 70px #0ba9ca, 0 0 80px #FF4B2B;
  }
}

@keyframes blink {
  100% {
    box-shadow: 0 0 3px #fff, 0 0 10px #fff, 0 0 20px #fff, 0 0 40px #FF4B2B,
      0 0 70px #FF4B2B, 0 0 80px #FF4B2B;
  }
}
</style>
    
</head>

<body>
    <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
    for(Cookie cookie : cookies)
    {
	if(cookie.getName().equals("user")) 
            userName = cookie.getValue();
    }
}
if(userName == null) 
    response.sendRedirect("authenticate_user.jsp");
    %>
    <div class="wrapper">

        <!-- Page Content Holder -->
        <div id="content">

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="navbar-btn">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>
                    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-align-justify"></i>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="fetchpasswords">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Profile</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Account Settings</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="http://localhost:8888/lockWise/user_logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            
            <div class="bg-img glow"></div>
            
            <div class="position-absolute bottom-right">
               <!-- <a href="#" class="social-button" target="_blank">
                  +
                </a> -->
                <form class="form-inline" onsubmit="openModal()" id="myForm">
                    <button type="submit" class="social-button">+</button>
                </form>
            </div>
            
        </div>
    </div>

     <!-- Modal to show passwords -->     
     <div class="modal fade" id="passModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">     
            <div class="modal-content">
                <%  if(request.getAttribute("data") != null)
                {
                ArrayList<util> u = (ArrayList<util>)request.getAttribute("data"); 
                for (int i=0; i <u.size(); i++)
                {
                      
                %>
                <div class="modal-header text-center" style="background-color:#FF2400">
                  <h4 class="modal-title w-100" style="font-weight: 150px; font-size: 20px;">----- Password -----</h4>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                
                <div class="modal-body mx-3">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <span class="fa fa-link form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).url %> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <span class="fa fa-address-book form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).website %> </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">  
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <span class="fa fa-tag form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).tag %> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <span class="fa fa-user form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).username %> </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">  
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <span class="fa fa-lock form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).password %> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="md-form mb-6">
                                <div class="form-group has-icon">
                                    <label data-error="wrong" data-success="right" for="form8" class="text-muted"><b>Notes:</b></label>
                                    <span class="fa fa-sticky-note form-control-feedback"></span>
                                    <p class="form-control"> <%=u.get(i).notes %> </p>
                                </div>
                            </div>
                        </div>
                  </div>
                </div>
                
                <div class="modal-footer">
                    <form action="fetchpasswords" >
                        <button type="submit" class="btn btn-default">Close</button>
                    </form>
                    <button type="button" class="btn btn-primary">Edit</button>
                </div>
              <% } 
                } %>
              </div>
            </div>
          </div><!-- modal -->
             
    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $(this).toggleClass('active');
            });
        });
        $(document).ready(function(){
		    $("#passModal").modal('show');
	    });
    </script>
</body>

</html>
