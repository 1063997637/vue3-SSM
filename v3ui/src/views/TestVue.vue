<template>
  <div class="content">
    <!-- 表单 -->
    <el-form
      :inline="true"
      :model="brand"
      class="demo-form-inline"
      align="left"
    >
      <el-form-item label="当前状态">
        <el-select v-model="conditionsearch.status" placeholder="当前状态">
          <el-option label="启用" value="1"></el-option>
          <el-option label="禁用" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌名称">
        <el-input
          v-model="conditionsearch.brandName"
          placeholder="品牌名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="公司名称">
        <el-input
          v-model="conditionsearch.companyName"
          placeholder="公司名称"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮 -->
    <el-row class="btn">
      <el-button type="danger" plain @click="batchdelete">批量删除</el-button>
      <el-button type="primary" plain @click="dialogFormVisible = true"
        >新增</el-button
      >
      <!--为新增按钮绑定弹出框  -->
      <el-dialog v-model="dialogFormVisible" title="品牌添加">
        <el-form :model="brand">
          <el-form-item label="品牌名称">
            <el-input v-model="brand.brandName" autocomplete="off" />
          </el-form-item>
          <el-form-item label="企业名称">
            <el-input v-model="brand.companyName" autocomplete="off" />
          </el-form-item>
          <el-form-item label="排名">
            <el-input v-model="brand.ordered" autocomplete="off" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="brand.description" type="textarea" />
          </el-form-item>
          <el-form-item label="状态">
            <el-switch
              v-model="brand.status"
              active-value="1"
              inactive-value="0"
            />
          </el-form-item>
        </el-form>
        <el-form-item>
          <el-button type="primary" @click="subnewbrand">创建</el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </el-form-item>
      </el-dialog>
    </el-row>

    <!-- 表格 -->
    <el-table
      :data="showdata"
      :row-class-name="tableRowClassName"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="序号" align="center"> </el-table-column>
      <el-table-column prop="brandName" label="品牌名称" align="center">
      </el-table-column>
      <el-table-column prop="companyName" label="企业名称" align="center">
      </el-table-column>
      <el-table-column prop="ordered" label="排序" align="center">
      </el-table-column>
      <el-table-column prop="status" label="当前状态" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" @click="update(scope.row)"
            ><el-icon><EditPen /></el-icon
          ></el-button>
          <el-button type="danger" @click="singledelete(scope.row)"
            ><el-icon><DeleteFilled /></el-icon
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 弹出修改窗格 -->
    <el-dialog v-model="updateFormVisible" title="修改信息">
      <el-form :model="brandshow">
        <el-form-item label="品牌名称" placeholder="brandshow.brandName">
          <el-input v-model="brandshow.brandName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业名称" placeholder="brandshow.companyName">
          <el-input v-model="brandshow.companyName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="排名" placeholder="brandshow.ordered">
          <el-input v-model="brandshow.ordered" autocomplete="off" />
        </el-form-item>
        <el-form-item label="备注" placeholder="brandshow.description">
          <el-input v-model="brandshow.description" type="textarea" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch
            v-model="brandshow.status"
            :active-value="1"
            :inactive-value="0"
          />
        </el-form-item>
      </el-form>
      <el-form-item>
        <el-button type="primary" @click="updatecommit">修改</el-button>
        <el-button @click="updateFormVisible = false">取消</el-button>
      </el-form-item>
    </el-dialog>

    <!-- 分页 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.tableData.length"
    >
    </el-pagination>
  </div>
</template>


<script>
import axios from "axios";
export default {
  data() {
    return {
      conditionsearch: {
        status: "",
        brandName: "",
        companyName: "",
      },
      updateFormVisible: false,
      dialogFormVisible: false,
      pageSize: 5,
      currentPage: 1,
      brand: {
        status: "",
        brandName: "",
        companyName: "",
        ordered: "",
        description: "",
      },
      brandshow: {
        id: "",
        status: "",
        brandName: "",
        companyName: "",
        ordered: "",
        description: "",
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
      showdata: [],
      multipleSelection: [],
    };
  },
  methods: {
    //添加成功弹出
    addsuccess() {
      this.$message({
        showClose: true,
        message: "添加成功",
        type: "success",
      });
    },
    //批量删除成功弹出
    btdelsuccess() {
      this.$message({
        showClose: true,
        message: "批量删除成功",
        type: "success",
      });
    },
    //单删除成功弹出
    delsuccess() {
      this.$message({
        showClose: true,
        message: "删除成功",
        type: "success",
      });
    },
    //修改成功弹出
    updatesuccess() {
      this.$message({
        showClose: true,
        message: "修改成功",
        type: "success",
      });
    },
    //改变每页条数
    handleSizeChange(val) {
      this.pageSize = val;
      this.changeshowdata();
      console.log(this.showdata);
    },
    //改变当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.changeshowdata();
    },
    //行变色功能
    tableRowClassName({ rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    // 改变页面当前展示的数据，实现分页
    changeshowdata() {
      let showindex = (this.currentPage - 1) * this.pageSize;
      this.showdata = [];
      for (let i = showindex; i < showindex + this.pageSize; i++) {
        this.showdata.push(this.tableData[i]);
      }
    },
    //添加选中状态
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //批量删除
    batchdelete() {
      var ids = [];
      for (let i = 0; i < this.multipleSelection.length; i++) {
        ids.push(this.multipleSelection[i].id);
      }
      axios({
        url: "http://localhost:8090/mysystem/brand/BatchDelete",
        method: "post",
        data: ids,
      }).then((response) => {
        if (response.data == "success") {
          this.btdelsuccess();
        }
      });
      console.log(ids);
      //刷新表格
      this.reflesh();
    },
    //单个删除
    singledelete(row) {
      console.log(row);
      //row直接就是一个json对象了
      axios({
        url: "http://localhost:8090/mysystem/brand/SingleDelete",
        method: "post",
        data: row.id,
      }).then((response) => {
        if (response.data == "success") {
          this.delsuccess();
        }
      });
      //刷新表格
      this.reflesh();
    },
    //修改框弹出
    update(row) {
      this.brandshow = row;
      this.updateFormVisible = true;
    },
    //修改提交
    updatecommit() {
      console.log(this.brandshow);
      axios({
        url: "http://localhost:8090/mysystem/brand/Update",
        method: "post",
        data: this.brandshow,
      }).then((response) => {
        if (response.data == "success") {
          this.updatesuccess();
        }
      });
      //刷新表格
      this.reflesh();
      this.updateFormVisible = false;
    },
    //条件查询提交
    onSubmit() {
      console.log(this.conditionsearch);
       axios({
        url: "http://localhost:8090/mysystem/brand/SelectByCondition",
        method: "post",
        data: this.conditionsearch,
      }).then((response) => {
        // console.log(response.data);
        this.tableData = response.data;
      });
      //刷新查询表格

      setTimeout(() => {
        this.changeshowdata();
      }, 1000);
    },
    //新增数据
    subnewbrand() {
      this.dialogFormVisible = false;
      console.log(this.brand);
      axios({
        url: "http://localhost:8090/mysystem/brand/add",
        method: "post",
        data: this.brand,
      }).then((response) => {
        if (response.data == "success") {
          this.addsuccess();
        }
      });
      console.log("完成");
      //清空保存的数据，以免第二次提交出错
      this.brand = {
        status: "",
        brandName: "",
        companyName: "",
        ordered: "",
        description: "",
      };
      //刷新表格
      this.reflesh();
    },
    getallinfo() {
      axios
        .get("http://localhost:8090/mysystem/brand/SelectAll")
        .then((response) => {
          this.tableData = response.data;
        });
    },
    reflesh() {
      //刷新表格
      setTimeout(() => {
        this.getallinfo();
      }, 1000);
      setTimeout(() => {
        this.changeshowdata();
      }, 2000);
    },
  },
  mounted() {
    this.getallinfo();
    setTimeout(() => {
      this.changeshowdata();
    }, 2000);
  },
};
</script>

<style>
.content {
  margin-top: 1%;
}
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>