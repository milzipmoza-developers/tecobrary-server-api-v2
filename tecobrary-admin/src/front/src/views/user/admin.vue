<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="검색할 이메일 주소">
        <el-input v-model="queryForm.email"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSearchRequest">검색</el-button>
        <el-button type="success" @click="onAddBtnClickHandler">새로 등록하기</el-button>
      </el-form-item>
    </el-form>
    
    <el-table
      empty-text="해당하는 어드민 유저가 없습니다"
      :data="adminUser.content"
      style="width: 100%; min-height: 400px;"
      highlight-current-row
      fit
    >
      <el-table-column align="center" label="번호" width="100">
        <span slot-scope="scope">{{ scope.row.id }}</span>
      </el-table-column>
      <el-table-column align="center" label="이메일">
        <span slot-scope="scope" class="link-type">{{ scope.row.email }}</span>
      </el-table-column>
      <el-table-column align="center" label="이름">
        <span slot-scope="scope">{{ scope.row.name }}</span>
      </el-table-column>
      <el-table-column align="center" label="권한">
        <span slot-scope="scope">{{ scope.row.role }}</span>
      </el-table-column>
    </el-table>
    
    <!--  페이지네이션  -->
    <div class="block" style="margin: 16px;">
      <el-pagination
        :current-page="queryForm.page"
        :page-sizes="[10, 20]"
        :page-size="queryForm.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="adminUser.totalElements"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    
    <el-dialog title="새로운 어드민 정보" :visible.sync="newAdmin.visible">
      <el-form :model="newAdmin" :rules="newAdmin.rules" ref="newAdmin">
        <el-form-item label="등록할 이메일 주소" prop="email">
          <el-input v-model="newAdmin.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="newAdmin.visible = false">닫기</el-button>
        <el-button type="warning" @click="onAdminEnrollRequest">등록하기</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {getAdmin, createAdmin} from "@/api/admin";

  export default {
    data: () => {
      const validEmail = (email) => {
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
      }
      
      const checkMailForm = (rule, value, callback) => {
        if (value === '') {
          callback(new Error("어드민으로 등록할 이메일을 입력해주세요."))
        } else if (!validEmail(value)) {
          callback(new Error("이메일 주소를 입력해주세요."))
        } else {
          callback()
        }
      }

      return {
        queryForm: {
          email: '',
          page: 0,
          size: 10
        },

        adminUser: {
          totalElements: 1,
          totalPages: 1,
          number: 10,
          content: []
        },

        newAdmin: {
          visible: false,
          rules: {
            email: [
              {validator: checkMailForm, trigger: 'blur'}
            ]
          },
          email: ''
        }
      }
    },
    
    async beforeMount() {
      this.getAdmins()
    },

    methods: {
      async getAdmins() {
        try {
          const response = await getAdmin(this.queryForm)
          const {content, totalElements, totalPages, number} = response
          this.adminUser.content = content
          this.adminUser.totalElements = totalElements
          this.adminUser.totalPages = totalPages
          this.adminUser.number = number
        } catch (e) {
          console.log(e)
        }
      },
      
      onSearchRequest() {
        console.log("search")
      },

      onAddBtnClickHandler() {
        this.newAdmin.email = ''
        this.newAdmin.visible = true
      },

      onAdminEnrollRequest() {
        this.$refs["newAdmin"].validate(async (valid) => {
          if (valid) {
            try {
              const response = await createAdmin({email: this.newAdmin.email})
              console.log(response)
              this.getAdmins()
            } catch (e) {
              console.log(e)
            }
            this.newAdmin.visible = false
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

      handleSizeChange(size) {
        this.queryForm.size = size
      },

      handleCurrentChange(page) {
        this.queryForm.page = page
      }
    }
  }
</script>