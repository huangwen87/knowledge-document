<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="navbar">
          <div class="navbar-inner">
              <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
                  <a class="brand" href="#"> <img alt="Charisma Logo" src="<c:url value="resources/img/logo20.png"/>" /> <span>舆情后台</span></a>
            <!-- theme selector starts -->
               <div class="btn-group pull-right theme-container">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone"> 更换主题 / 皮肤</span>
						<span class="caret"></span>
					</a>
                   <ul class="dropdown-menu" id="themes">
                       <li>
                           <a data-value="classic" href="#"><i class="icon-blank"></i> 经典</a>
                       </li>
                       <li>
                           <a data-value="cerulean" href="#"><i class="icon-blank"></i> 天蓝色</a>
                       </li>
                       <li>
                           <a data-value="cyborg" href="#"><i class="icon-blank"></i> 亮黑色</a>
                       </li>
                       <li>
                           <a data-value="redy" href="#"><i class="icon-blank"></i> 玫瑰红</a>
                       </li>
                       <li>
                           <a data-value="journal" href="#"><i class="icon-blank"></i> 浅灰色 </a>
                       </li>
                       <li>
                           <a data-value="simplex" href="#"><i class="icon-blank"></i> 淡青色</a>
                       </li>
                       <li>
                           <a data-value="slate" href="#"><i class="icon-blank"></i> 玄青色 </a>
                       </li>
                       <li>
                           <a data-value="spacelab" href="#"><i class="icon-blank"></i> 太空白 </a>
                       </li>
                       <li>
                           <a data-value="united" href="#"><i class="icon-blank"></i> 红褐色 </a>
                       </li>
                   </ul>
               </div>
               <!-- theme selector ends -->
            <!-- user dropdown starts -->
               <div class="btn-group pull-right">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> 管理员</span>
						<span class="caret"></span>
				    </a>
                   <ul class="dropdown-menu">
                       <li>
                           <a href="#">资料</a>
                       </li>
                       <li class="divider"></li>
                       <li>
                           <a href="log">退出</a>
                       </li>
                   </ul>
               </div>
               <!-- user dropdown ends -->
            <div class="top-nav nav-collapse">
                <ul class="nav">
                    <li>
                        <a href="#">大智慧</a>
                    </li><!--
                    <li>
                        <form class="navbar-search pull-left">
                            <input placeholder="Search" class="search-query span2" name="query" type="text">
                        </form>
                    </li> -->
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<!-- topbar ends -->