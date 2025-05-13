<script setup lang="ts">
import {RouterView} from 'vue-router'
</script>

<template>
  <el-row :gutter="20" class="bbd-fullheight">
    <el-col :span="3">
      <el-menu :router="true" class="bbd-fullheight">
        <el-menu-item index="/">
          <el-icon>
            <Aim/>
          </el-icon>
          <span>AI界面</span>
        </el-menu-item>
        <el-menu-item index="/about">
          <el-icon>
            <List/>
          </el-icon>
          <span>传统界面</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="20">
      <RouterView/>
    </el-col>
  </el-row>
</template>
