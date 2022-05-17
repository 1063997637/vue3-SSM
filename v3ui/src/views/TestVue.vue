<template>
  <!-- 表单 -->
  <el-form :inline="true" :model="brand" class="demo-form-inline">
    <el-form-item label="当前状态">
      <el-select v-model="brand.status" placeholder="当前状态">
        <el-option label="启用" value="1"></el-option>
        <el-option label="禁用" value="0"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="品牌名称">
      <el-input v-model="brand.brandName" placeholder="品牌名称"></el-input>
    </el-form-item>
    <el-form-item label="公司名称">
      <el-input v-model="brand.companyName" placeholder="公司名称"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>

  <!-- 按钮 -->
  <el-row class="btn">
    <el-button type="danger" plain>批量删除</el-button>
    <el-button type="primary" plain>新增</el-button>
  </el-row>

  <!-- 表格 -->
  <el-table
    :data="tableData"
    :row-class-name="tableRowClassName"
    @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection"></el-table-column>
    <el-table-column type="index"></el-table-column>
    <el-table-column prop="brandName" label="品牌名称" align="center">
    </el-table-column>
    <el-table-column prop="companyName" label="企业名称" align="center">
    </el-table-column>
    <el-table-column prop="ordered" label="排序" align="center">
    </el-table-column>
    <el-table-column prop="status" label="当前状态" align="center">
    </el-table-column>
    <el-table-column label="操作" align="center">
      <el-button type="primary"
        ><el-icon><EditPen /></el-icon
      ></el-button>
      <el-button type="danger"
        ><el-icon><DeleteFilled /></el-icon
      ></el-button>
    </el-table-column>
  </el-table>
</template>


<script>
export default {
  methods: {
    tableRowClassName({ rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    onSubmit() {
      console.log(this.brand);
    },
  },
  data() {
    return {
      brand: {
        status: "",
        brandName: "",
        companyName: "",
      },
      tableData: [
        {
          brandName: "华为",
          companyName: "华为有限公司",
          ordered: "1",
          status: 1,
        },
        {
          brandName: "小米",
          companyName: "小米有限公司",
          ordered: "1",
          status: 1,
        },
        {
          brandName: "三星",
          companyName: "三星有限公司",
          ordered: "1",
          status: 1,
        },
        {
          brandName: "OPPO",
          companyName: "OPPO有限公司",
          ordered: "1",
          status: 1,
        },
        {
          brandName: "VIVO",
          companyName: "VIVO有限公司",
          ordered: "1",
          status: 1,
        },
      ],
      multipleSelection: [],
    };
  },
};
</script>

<style>
.demo-form-inline {
margin-top: 1%;
float: left;
}
.btn{
    float: left;
}
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>