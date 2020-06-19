<template id="user-profile">
    <app-frame>
        <div class="container">
            <b-form validated="" id="passwordChangeForm" @submit.prevent="changePassword" class="container-fluid">
                <b-form-group id="userIdG" label="User Id">
                    <b-form-input id="userId" v-model="user.id" type="text" required="true" />
                </b-form-group>
                <b-form-group id="userNameG" label="User Name">
                    <b-form-input id="userName" v-model="user.name" type="text" required="true" />
                </b-form-group>
                <b-form-group id="userEmailId" label="User Email">
                    <b-form-input id="email" v-model="user.email" type="text" required="true" />
                </b-form-group>
                <b-form-group id="userDateG" label="User Date">
                    <b-form-datepicker id="example-datepicker" v-model="user.userDetails.dateOfBirth" class="mb-2" required="true"></b-form-datepicker>
                </b-form-group>
                <b-form-group id="userSaleryG" label="User Salery">
                    <b-form-input id="salery" v-model="user.userDetails.salery" type="text" required="true" />
                </b-form-group>
                <b-button type="submit" variant="primary">Submit</b-button>
            </b-form>
        </div>
    </app-frame>
</template>
<script>
    Vue.component("user-profile", {
        template: "#user-profile",
        data: () => ({
            user: null,
        }),
        created() {
            const userId = this.$javalin.pathParams["user-id"];
            fetch(`/api/users/${userId}`)
                .then(res => res.json())
                .then(json => this.user = json)
                .catch(() => alert("Error while fetching user"));
        }
    });
</script>
