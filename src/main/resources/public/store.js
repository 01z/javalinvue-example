
export const state = {
    auth: {
        user: '',
        authenticated: false
    },
    context: {
        title: 'JavalinVue'
    },
    items: [
        { name: "blah" },
        { name: "blub" },
    ]
};

/*
Mutations are functions responsible in directly mutating store state
 */
const mutations = {
    SET_NAV(state, payload) {
        state.context = { title: payload };
    },
    SET_AUTH(state, payload) {
        state.auth = { user: payload, authenticated: payload!=null && payload!=="" };
    },
    ADD_ITEM(state, payload) {
        state.items.push(payload);
    }
};

/*
Actions exist to call mutations. Actions are also responsible
in performing any or all asynchronous calls prior to committing to mutations.
 */
const actions = {
    addItem(context, item) {
        context.commit("ADD_ITEM", item);
    }
};

/*
Getters are to a Vuex store what computed properties are to a Vue component.
Getters are primarily used to perform some calculation/manipulation to store
state before having that information accessible to components.
 */
const getters = {
    items(state) {
        return state.items;
    },
    auth(state) {
        return state.auth;
    },
    context(state) {
        return state.context;
    }
};

/*
Now, wherever you initialize your store (and this may vary a lot from project to project,)
import vuex-persist and initialize it as a plugin for Vuex
 */
Vue.use(Vuex);


const vuexLocalStorage = new window.VuexPersistence.VuexPersistence({
    key: 'vuex', // The key to store the state on in the storage provider.
    storage: window.sessionStorage, // or window.sessionStorage or localForage
    // Function that passes the state and returns the state with only the objects you want to store.
    // reducer: state => state,
    // Function that passes a mutation and lets you decide if it should update the state in localStorage.
    // filter: mutation => (true)
});

/*
Vuex is a Flux-like, state management library built solely for use with Vue.
 */
export const store = new Vuex.Store({
    state,
    mutations,
    actions,
    getters,
    plugins: [vuexLocalStorage.plugin]
});
