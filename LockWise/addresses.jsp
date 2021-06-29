<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

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

.scrollcol {
    height: 400px;
    overflow-y: auto;
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
        <!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
               <span class="login-title">
                  &nbsp;Lock Wise ...&nbsp;
               </span>   
            </div>

            <ul class="list-unstyled components">
                <p>EXPLORE SECRET VAULT</p>
                <li>
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li>
                            <a href="dashboard.jsp">All Items</a>
                        </li>
                        <li>
                            <a href="#">User Profile</a>
                        </li>
                        <li>
                            <a href="#">Help</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="passwords.jsp">Passwords</a>
                </li>
                <li>
                    <a href="notes.jsp">Notes</a>
                </li>
                <li class="active">
                    <a href="addresses.jsp">Addresses</a>
                </li>
                <li>
                    <a href="#financeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Finance</a>
                    <ul class="collapse list-unstyled" id="financeSubmenu">
                        <li>
                            <a href="paymentcards.jsp">Payment Cards</a>
                        </li>
                        <li>
                            <a href="bankaccounts.jsp">Bank Accounts</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#generateSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Advanced Settings</a>
                    <ul class="collapse list-unstyled" id="generateSubmenu">
                        <li>
                            <a href="generatepassword.jsp">Generate Secure Password</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>

            <ul class="list-unstyled CTAs">
                <li>
                    <a href="http://localhost:8888/lockWise/user_logout" class="download">
                        Log out
                    </a>
                </li>
                <li>
                    <a href="https://bootstrapious.com/p/bootstrap-sidebar" class="article">Back to article</a>
                </li>
            </ul>
        </nav>

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
                                <a class="nav-link" href="#">Home</a>
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
            
            <h2>Password Shield</h2>
            <p>Effortless Security from Anywhere. </p>
            <p>Life is happening online. Work. Play. Family and friends. LockWise puts your digital life at your fingertips, simply and securely.</p>
            
            <div class="line"></div>

            <h2>Personal</h2>
            <p>Store all of you and your family's passwords across all your devices without sacrificing security.</p>

            <div class="line"></div>

            <h2>Business</h2>
            <p>Connect employees to their work while maintaining complete visibility and control.</p>

            <div class="line"></div>
            
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
    
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" style="height:500px !important;" role="document">     
            <div class="modal-content">
                <form action="" >
                <div class="modal-header text-center" style="background-color:#FF2400">
                  <h4 class="modal-title w-100" style="font-weight: 150px; font-size: 20px;">Add address</h4>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                
                <div class="modal-body mx-3 my-0">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="row">
                                <div class="md-form mb-4">
                                <div class="form-group has-icon">
                                    <span class="fa fa-address-book form-control-feedback"></span>
                                    <input type="text" class="form-control" placeholder="Name">
                                </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="md-form mb-8">
                                <div class="form-group has-icon">
                                    <span class="fa fa-tag form-control-feedback"></span>
                                    <input type="text" class="form-control" placeholder="Tag">
                                </div>
                            </div>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="scrollcol">
                            <table class="table table-striped"  style="height:300px !important;">
                            <thead>
                            </thead>
                            <tbody>
                                <tr>
                                    <th>Title</th>
                                    <td>
                                      <select name="salutation" class="custom-select mb-3">
                                        <option >Mr</option>
                                        <option value="volvo">Mrs</option>
                                        <option value="fiat">Ms</option>
                                        <option value="audi">Dr</option>
                                      </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>First Name</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Last Name</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Gender</th>
                                    <td>
                                        <select name="gender" class="custom-select mb-3">
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="no">Prefer not to say</option>
                                      </select>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Address 1</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Address 2</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>City/Town</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Country</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>State</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>ZIP/Postal Code</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="text" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Email Address</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="email" class="form-control" placeholder="">
                                        </div>
                                    </td>
                                </tr>  
                                <tr>
                                    <th>Phone</th>
                                    <td>
                                        <div class="form-group">
                                        <input type="tel" class="form-control" placeholder="" pattern="[0-9]{10}">
                                        </div>
                                    </td>
                                </tr>  
                            </tbody>
                            </table>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
                <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
                </form>
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
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
        });
        $('#myForm').on('submit', function(e){
            $('#myModal').modal('show');
            e.preventDefault();
        });
    </script>
</body>

</html>
