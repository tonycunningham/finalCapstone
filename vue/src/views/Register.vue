<template>
  <div id="register" class="register-center-box">
    <div class="register-box">
    <form @submit.prevent="register" class="register-form-grid">
      <h1 class="register-create-account">Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="register-form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="register-form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="register-form-input-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit" class="create-box-button">Create Account</button>
      <div class="login-container">
      <p class="login-box-text"><router-link class="login" :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      </div>
    </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

.register-center-box {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 65vh;
}

.register-box {
  background-color: #9C27B0;
  padding: 2rem;
  max-width: 400px;
  margin: auto;
  border-radius: 5px;
  color: white;
  text-align: center;
}

.create-box-button {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
  width: 100px;
  margin: 0 auto;
  border-radius: 75px;
  color: white;
  background-color: rgba(255, 255, 255, 0.2);
}

.create-box-button:hover {
  background-color: rgba(255, 255, 255, 0.4);
}

.login-box-text {
  margin-top: 1rem;
}

.login {
  color: white;
  margin: 0;
  font-size: 15px;
  padding: 0.25rem;
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  border: none;
  padding: 0.5rem;
  margin-top: 0.5rem;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 75px;
  border: 2px solid rgba(255, 255, 255, 0.2);
}

.login-container:hover {
  background-color: rgba(255, 255, 255, 0.4);
}

.register-form-grid {
  display: grid;
  gap: 1rem;
}

.register-create-account {
  text-align: center;
}

.register-form-input-group {
  display: grid;
  gap: 0.5rem;
}

label, input {
  justify-self: center;
}

.register-form-input-group label {
  grid-row: 1;
}

.register-form-input-group input {
  grid-row: 2;
  width: 100%;
}

</style>
