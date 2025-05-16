<script setup lang="ts">
import {ref} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import axios from "axios";

interface Props {
  data: any;
  dataType: 'text' | 'ledger' | 'stat' | 'tip';
  role: 'Me' | '叨';
}

defineProps<Props>();

const emit = defineEmits<{
  (e: 'tipClick', tip: string): void
}>();

const handleTipClick = (tip: string) => {
  emit('tipClick', tip);
};

const title = ref("费用已记录")
const disableDelete = ref(false)
const showDeleteBtn = ref(true)

const handleDeleteLedger = (data: any) => {
  disableDelete.value = true;
  ElMessageBox
    .confirm('确认要删除费用"' + data.desc + '"么?')
    .then(() => {
      axios({
        method: 'delete',
        url: '/bbd-server/ledgers/' + data.ledgerId
      }).then((response) => {
        console.log(response);

        ElMessage({
          message: '费用记录已删除',
          type: 'success',
        });
        title.value = "费用已删除"
        showDeleteBtn.value = false;
      }).catch(function (error) {
        ElMessage({
          message: '请求服务器出错,请稍后再试!',
          type: 'error',
        });
        disableDelete.value = false;
      });
    });
};
</script>

<template>
  <el-card class="card">
    <el-avatar size="small" shape="square">{{ role }}</el-avatar>
    <div v-if="dataType === 'text'">
      <p>
        <el-text>{{ data.content }}</el-text>
      </p>
    </div>
    <div v-else-if="dataType === 'ledger'">
      <el-descriptions
        class="margin-top"
        :title="title"
        :column="3"
        border
      >
        <template #extra>
          <el-button @click="handleDeleteLedger(data)" v-if="showDeleteBtn" :disabled="disableDelete" type="primary">
            搞错了，我要删除
          </el-button>
        </template>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              时间
            </div>
          </template>
          {{ data.feeDate }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              金额
            </div>
          </template>
          {{ data.amount }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              分类
            </div>
          </template>
          {{ data.feeTypeName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              描述
            </div>
          </template>
          {{ data.desc }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div v-else-if="dataType === 'stat'">

    </div>
    <div v-else-if="dataType === 'tip'">
      <p>
        <el-text>{{ data.content }}</el-text>
      </p>
      <div class="flex gap">
        <el-tag type="primary" v-for="tip in data.tips" @click="handleTipClick(tip)">{{ tip }}</el-tag>
      </div>
    </div>
  </el-card>
</template>
<style scoped>
.card {
  width: 90%;
  max-width: 90%;
  margin-bottom: 1rem;
}

.gap {
  gap: 0.5rem;
}

.flex {
  display: flex;
}

.cell-item {
  display: flex;
  align-items: center;
}
</style>
