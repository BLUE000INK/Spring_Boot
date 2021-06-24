<template>
  <div class="info_form">
    <label style="font-size: 50px;font-family: 隶书;position: relative;left: 35%;right: 35%">用户信息</label>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" label-width="100px">
      <el-form-item prop="r_name" label="用户名:">
        <el-input v-model="ruleForm.r_name" placeholder="用户名" readonly="readonly" clearable></el-input>
      </el-form-item>
      <el-form-item prop="r_pwd" label="密码:">
        <el-input v-model="ruleForm.r_pwd" placeholder="密码" show-password clearable></el-input>
      </el-form-item>
      <el-form-item prop="r_phone" label="联系电话:">
        <el-input type="text" v-model="ruleForm.r_phone" placeholder="联系电话" maxlength="11" show-word-limit
                  clearable></el-input>
      </el-form-item>
      <el-form-item prop="r_email" label="邮箱:">
        <el-input v-model="ruleForm.r_email" placeholder="邮箱" clearable></el-input>
      </el-form-item>
      <el-form-item prop="r_city" label="居住城市:">
        <el-input v-model="ruleForm.r_city" placeholder="居住城市" clearable></el-input>
      </el-form-item>
      <el-form-item label="性别:">
        <el-radio-group v-model="ruleForm.r_gender" size="large">
          <el-radio border label="1">男生</el-radio>
          <el-radio border label="0">女生</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="r_bank" label="银行账户:">
        <el-input v-model="ruleForm.r_bank" placeholder="银行账号" maxlength="16" minlength="16" show-word-limit
                  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="btn_updateForm('ruleForm')" round>信息修改</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
  export default {
    data () {
      var checkNum = (rule, value, callback) => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'))
        }
      }
      return {
        ruleForm: {
          r_name: '',
          r_pwd: '',
          r_phone: '',
          r_email: '',
          r_city: '',
          r_gender: '1',
          r_bank: '',
        },
        rules: {
          r_name: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          r_pwd: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
          r_phone: [
            {required: true, message: '请输入电话号码', trigger: 'blur'},
            {min: 11, max: 11, message: '请输入11位手机号', trigger: ['blur', 'change']},
            {validator: checkNum, trigger: 'blur'}
          ],
          r_email: [
            {required: true, message: '请输入邮箱地址', trigger: 'blur'},
            {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
          ],
          r_city: [
            {required: true, message: '请输入居住地', trigger: 'blur'}
          ],
          r_bank: [
            {required: true, message: '请输入银行账户', trigger: 'blur'},
            {min: 16, max: 16, message: '请输入16位银行账户', trigger: ['blur', 'change']},
            {validator: checkNum, trigger: 'blur'}
          ],
        }
      }
    },
    created () {
      this.getUserinfo()
    },


    methods: {
      getUserinfo () {
        this.$axios.post('http://localhost:8080/users/findUserinfo', {
          userName: sessionStorage.getItem('userName')
        }).then(response => {
          console.log(response)
          this.ruleForm.r_name = response.data.userName
          this.ruleForm.r_pwd = response.data.userPwd
          this.ruleForm.r_email = response.data.userEmail
          this.ruleForm.r_phone = response.data.userPhone
          this.ruleForm.r_city = response.data.userCity
          this.ruleForm.r_bank = response.data.userBank
          this.ruleForm.r_gender = response.data.userGender + ''
          console.log('r_gender=' + this.ruleForm.r_gender)
        })
        console.log(this.sessionStorage.getItem('userName'))
      },
    },
  }
</script>
<style>
  .info_form {
    margin-left: 33%;
    margin-right: 33%;
    position: relative;
  }
</style>
