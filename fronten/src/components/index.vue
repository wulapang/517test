<template>
        <el-container style="margin-left: 1px" >
            <el-header>职员信息管理系统</el-header>
            <el-container>
                <el-aside width="250px" style="height: auto">
                    <el-menu
                        default-active="/index" router>
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>职工管理</span>
                            </template>
                            <el-menu-item index="1-1">职工统计</el-menu-item>
                            <el-menu-item index="/index/table">职工列表</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
</template>