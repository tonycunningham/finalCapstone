<template>
  <div id="login" class="center-box">
    <div class="box">
    <form @submit.prevent="login" class="form-grid">
      <h1 class="sign-in-text">Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit" class="box-button">Sign in</button>
      <div class="registration-container">
      <p class="box-text">
      <router-link class="registration" :to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
      </div>
    </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.center-box {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 60vh;
}

.box {
  background-color: #9C27B0;
  padding: 2rem;
  max-width: 400px;
  margin: auto;
  border-radius: 5px;
  color: white;
  text-align: center;
}

.box-button {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
  width: 100px;
  margin: 0 auto;
  border-radius: 75px;
  color: white;
  background-color: rgba(255, 255, 255, 0.2);
}

.box-button:hover {
  background-color: rgba(255, 255, 255, 0.4);
}

.box-text {
  margin-top: 1rem;
}

.registration{
  color: white;
  margin: 0;
  font-size: 15px;
  padding: 0.25rem;
}

.registration-container {
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

.registration-container:hover {
  background-color: rgba(255, 255, 255, 0.4);
}

.form-grid {
  display: grid;
  gap: 1rem;
}

.sign-in-text {
  text-align: center;
}

.form-input-group {
  display: grid;
  gap: 0.5rem;
}

label, input {
  justify-self: center;
}

.form-input-group label {
  grid-row: 1;
}

.form-input-group input {
  grid-row: 2;
  width: 100%;
}

</style>