<template>
  <div class="main-layout">
    <div class="layout-sidebar" :class="{ collapsed: isCollapsed }">
      <div class="logo-area">
        <el-icon class="logo-icon"><OfficeBuilding /></el-icon>
        <span v-if="!isCollapsed" class="logo-text">供应商管理系统</span>
      </div>

      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapsed"
        :collapse-transition="false"
        class="sidebar-menu"
        background-color="#001529"
        text-color="#fff"
        active-text-color="#409eff"
        router
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><Management /></el-icon>
            <span>基础管理</span>
          </template>
          <el-menu-item index="/supplier">
            <el-icon><OfficeBuilding /></el-icon>
            <span>供应商管理</span>
          </el-menu-item>
          <el-menu-item index="/user">
            <el-icon><User /></el-icon>
            <span>用户管理</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <el-icon><DataAnalysis /></el-icon>
            <span>数据统计</span>
          </template>
          <el-menu-item index="/dashboard">
            <el-icon><PieChart /></el-icon>
            <span>数据概览</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>系统设置</span>
          </template>
          <el-menu-item index="/settings">
            <el-icon><Tools /></el-icon>
            <span>系统配置</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <div class="layout-main">
      <div class="layout-header">
        <div class="header-left">
          <el-button
            class="collapse-btn"
            :icon="isCollapsed ? Expand : Fold"
            @click="toggleCollapse"
            text
          />
          <el-breadcrumb separator="/">
            <el-breadcrumb-item
              v-for="(item, index) in breadcrumbs"
              :key="index"
              :to="index === breadcrumbs.length - 1 ? undefined : item.path"
            >
              {{ item.title }}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              <el-avatar :size="32" icon="UserFilled" />
              <span class="username">管理员</span>
              <el-icon><CaretBottom /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">
                  <el-icon><User /></el-icon>个人中心
                </el-dropdown-item>
                <el-dropdown-item command="settings">
                  <el-icon><Setting /></el-icon>系统设置
                </el-dropdown-item>
                <el-dropdown-item divided command="logout">
                  <el-icon><SwitchButton /></el-icon>退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>

      <div class="layout-content">
        <router-view v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import {
  Fold,
  Expand,
  Management,
  OfficeBuilding,
  User,
  UserFilled,
  DataAnalysis,
  PieChart,
  Setting,
  Tools,
  CaretBottom,
  SwitchButton
} from '@element-plus/icons-vue'

const route = useRoute()
const isCollapsed = ref(false)

const activeMenu = computed(() => route.path)

const menuConfig = {
  '/supplier': [{ title: '首页', path: '/' }, { title: '基础管理' }, { title: '供应商管理' }],
  '/user': [{ title: '首页', path: '/' }, { title: '基础管理' }, { title: '用户管理' }],
  '/dashboard': [{ title: '首页', path: '/' }, { title: '数据统计' }, { title: '数据概览' }],
  '/settings': [{ title: '首页', path: '/' }, { title: '系统设置' }, { title: '系统配置' }]
}

const breadcrumbs = computed(() => {
  return menuConfig[route.path] || [{ title: '首页', path: '/' }]
})

const toggleCollapse = () => {
  isCollapsed.value = !isCollapsed.value
}

const handleCommand = (command) => {
  switch (command) {
    case 'profile':
      ElMessage.info('个人中心功能开发中')
      break
    case 'settings':
      ElMessage.info('系统设置功能开发中')
      break
    case 'logout':
      ElMessage.success('已退出登录')
      break
  }
}
</script>

<style lang="scss" scoped>
.main-layout {
  display: flex;
  height: 100vh;
  overflow: hidden;
}

.layout-sidebar {
  width: 220px;
  background-color: #001529;
  transition: width 0.3s;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  &.collapsed {
    width: 64px;
  }
}

.logo-area {
  height: 60px;
  display: flex;
  align-items: center;
  padding: 0 20px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  gap: 12px;

  .logo-icon {
    width: 32px;
    height: 32px;
    color: #409eff;
    font-size: 32px;
  }

  .logo-text {
    color: #fff;
    font-size: 16px;
    font-weight: 600;
    white-space: nowrap;
  }
}

.sidebar-menu {
  flex: 1;
  border-right: none;

  :deep(.el-menu) {
    border-right: none;
  }

  :deep(.el-sub-menu__title:hover) {
    background-color: rgba(64, 158, 255, 0.1) !important;
  }

  :deep(.el-menu-item:hover) {
    background-color: rgba(64, 158, 255, 0.1) !important;
  }

  :deep(.el-menu-item.is-active) {
    background-color: rgba(64, 158, 255, 0.2) !important;
    position: relative;

    &::before {
      content: '';
      position: absolute;
      left: 0;
      top: 0;
      bottom: 0;
      width: 3px;
      background-color: #409eff;
    }
  }
}

.layout-main {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background-color: #f0f2f5;
}

.layout-header {
  height: 60px;
  background: #fff;
  border-bottom: 1px solid #e6e8eb;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.collapse-btn {
  font-size: 20px;
  color: #606266;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 4px;
  transition: background-color 0.2s;

  &:hover {
    background-color: #f5f7fa;
  }

  .username {
    font-size: 14px;
    color: #606266;
  }
}

.layout-content {
  flex: 1;
  overflow: auto;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
