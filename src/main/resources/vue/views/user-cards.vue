<template id="user-cards">
    <app-frame>
        <div class="container">
                <b-card-group>
                    <div class="row">
                        <b-card deck v-for="user,i in users" :id="i"
                                v-bind:header="`${user.name}`"
                                class="text-center m-3"
                                >
                            <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
                        </b-card>
                    </div>
                </b-card-group>
        </div>
    </app-frame>
</template>
<script>
    Vue.component("user-cards", {
        template: "#user-cards",
        data: () => ({
            users: [],
            selectedUserIds: [],
        }),
        created() {
            fetch("/api/users")
                .then(res => res.json())
                .then(json => this.users = json)
                .catch(() => alert("Error while fetching users"));
        }
    });
</script>
<style>
</style>
