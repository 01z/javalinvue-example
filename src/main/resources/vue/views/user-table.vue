<template id="user-table">
<!--
tableFields: [
                {   key: 'partner', label: 'Partner', sortable: true},
                {   key: 'activationDate', label: 'Aktivierungs-Datum', },
                {   key: 'fileName', label: 'Dateiname',sortable: true},
                {
                    key: 'fileSize', label: 'Dateigröße',
                    formatter: (value, key, item) => {
                        return convertFileSize(item.fileSize)
                    },
                },
                {   key: 'state', label: 'Status', sortable: true,
                    filterByFormatted: true
                },
                {
                    key: 'action'
                },
                {
                    key: 'updated',
                    label: 'Timestamp',
                    formatter: (value, key, item) => {
                        return formatItemTimestamp(item,false);
                    }
                }
-->
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

        <b-table ref="eventsTable"
                 sticky-header
                 show-empty
                 sort-icon-left
                 :tbody-tr-class="eventRowClass"
                 :busy="isLoading"
                 :items="displayEvents"
                 :fields="tableFields" primary-key="id"
                 :filter="filterText"
                 :filter-included-fields="['fileName','state','partner']"
        >
            <!--
                 :filter-function="filterTableByPartner"
                 :filter="filterPartner"
                 selectable
                 :select-mode="single"
                 @row-selected="onEventRowSelected"
            - ->

            <template v-slot:table-busy>
                <div class="text-center text-dark my-2">
                    <b-spinner class="align-middle"></b-spinner>
                    <strong>Loading ...</strong>
                </div>
            </template>

            <template v-slot:cell(action)="row">
                <b-button :v-show="getRowAction(row)" size="sm" @click="resubmit(row)" class="mr-2">
                    Submit
                </b-button>
                <b-button size="sm" @click="row.toggleDetails">
                    {{ row.detailsShowing ? 'Hide' : 'Show' }} Details
                </b-button>
            </template>

            <template v-slot:cell(state)="data">
                <item-status :item="data.item"></item-status>
            </template>

            <template v-slot:empty-html="scope">
                <h4>No user data found</h4>
            </template>
            <template v-slot:empty-filtered-html="scope">
                <h4>No items matching filter</h4>
            </template>

            <template v-slot:row-details="row">
                <b-card>
                    <ul>
                        <li v-for="(value, key) in row.item" :key="key">{{ key }}: {{ value }}</li>
                    </ul>
                </b-card>
            </template>
        </b-table>
        -->
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
            <tr v-for="user in users" v-bind:class=
                    "[ user.state === 'ERROR' ? 'table-danger' : '',  user.state === 'WARN' ? 'table-warn' : '']">
                <td scope="row" class="text-center">
                    <input type="checkbox" :id="user.id" :value="user.id" v-model="selectedUserIds"/>
                </td>
                <td scope="row" class="text-center"><a :href="`/users/${user.id}}`">{{user.id}}</a></td>
                <td>{{user.name}}</td>
                <td>{{user.email}}</td>
                <td class="text-right">{{user.state}}</td>
                <td>
                    <div class="progress"><div
                            class="progress-bar progress-bar-striped bg-success"
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
    Vue.component("user-table", {
        template: "#user-table",
        data: () => ({
            users: [],
            selectedUserIds: [],
            /* prepared for b-table
            isLoading: false,
            filterText: "",
            tableFields: [
                {   key: 'id', label: 'ID', sortable: true },
                {   key: 'name', label: 'User Name',sortable: true},
                {   key: 'state', label: 'Status', sortable: true,
                    filterByFormatted: true
                    formatter: (value, key, item) => {
                        return xxx(value)
                    },
                },
                {
                    key: 'action'
                },
            ],
            */
        }),
        computed: {
            partnerNames(){
                return Object.values(this.partners).map((it) => it.name)
            },
/*
            ...Vuex.mapGetters([
            ])
*/
        },
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
