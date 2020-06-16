<template id="user-overview">
    <app-frame>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
        <table class="table table-hover table-sm">
            <thead class="thead-light">
            <tr >
                <th scope="col" style="width: 1em" class="text-center">Select</th>
                <th scope="col" class="text-center">ID#</th>
                <th scope="col" >Name</th>
                <th scope="col" >Email</th>
                <th scope="col" class="text-right">State</th>
                <th scope="col" style="width: 50%">Quota</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="user in users" v-bind:class="mapStyle( user.state, 'table')">
                <td scope="row" class="text-center">
                    <input type="checkbox" :id="user.id" :value="user.id" v-model="selectedUserIds"/>
                </td>
                <td scope="row" class="text-center"><a :href="`/users/${user.id}`">{{user.id}}</a></td>
                <td>{{user.name}}</td>
                <td>{{user.email}}</td>
                <td class="text-right">{{user.state}}</td>
                <td>
                    <div class="progress"><div
                            class="progress-bar progress-bar-striped"
                            v-bind:class="mapStyle( user.state, 'bg')"
                            role="progressbar" :style="`width: ${user.quota}%`"
                            :aria-valuenow="user.quota" aria-valuemin="0" aria-valuemax="100">{{user.quota}} %</div></div>
                </td>
            </tr>
            </tbody>
        </table>

        <h4>Selection <span class="badge badge-secondary">{{ selectedUserIds.size }}</span></h4>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <div class="input-group-text">
                    <input type="text" v-model="selectedUserIds"/>
                </div>
            </div>
        </div>
    </app-frame>
</template>
<script>
    Vue.component("user-overview", {
        template: "#user-overview",
        data: () => ({
            users: [],
            selectedUserIds: [],
        }),
        created() {
            fetch("/api/users")
                .then(res => res.json())
                .then(json => this.users = json)
                .catch(() => alert("Error while fetching users"));
        },
        methods: {
            mapStyle: function(t,s) {
                return [ t === 'ERROR' ? `${s}-danger` : '',  t === 'WARN' ? `${s}-warning` : '']
            }
        }
    });
</script>
<style>
</style>
