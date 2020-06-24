<template id="app-header">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand to="/"><img src="/logo.png" width="30" height="30" alt="" loading="lazy">
                {{ title }}
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-item to="/users" replace>Users</b-nav-item>
                    <b-nav-item to="/cards" replace>Cards</b-nav-item>
                    <b-nav-item to="/console" replace>C0nsole</b-nav-item>
                    <b-nav-item href="/unknown" >Unknown</b-nav-item>
                    <b-nav-item v-if="auth.authenticated" href="/admin" disabled replace>Admin</b-nav-item>
                </b-navbar-nav>
                <!-- Right aligned nav items -->
                <!--<b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input class="mr-sm-2" placeholder="Search"></b-form-input>
                        <b-button class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>
                </b-navbar-nav>-->
            </b-collapse>
        </b-navbar>
    </div>
</template>
<script>
    Vue.component("app-header", {
        template: "#app-header",
        data: function () {
            return {
            }
        },
        computed: {
            context() {
                return this.$store.getters.context
            },
            auth() {
                return this.$store.getters.auth
            },
            title() {
                return this.context.title
            }
        }
    });
</script>
<style>

</style>
