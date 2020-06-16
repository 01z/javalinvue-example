<template id="app-header">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand href="/"><img src="/logo.png" width="30" height="30" alt="" loading="lazy">
                {{ $javalin.state.currentView }}
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-item href="/users">Users</b-nav-item>
                    <b-nav-item href="/cards">Cards</b-nav-item>
                    <b-nav-item href="/xxxxxxxxxx">XXXX</b-nav-item>
                    <b-nav-item v-if="$javalin.state.currentUser" href="/admin" disabled>Admin</b-nav-item>
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
    Vue.component("app-header", {template: "#app-header"});
</script>
<style>

</style>
