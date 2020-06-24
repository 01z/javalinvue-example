// 0. If using a module system (e.g. via vue-cli), import Vue and VueRouter
// and then call `Vue.use(VueRouter)`.

// 1. Define route components.
// These can be imported from other files
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// `Vue.extend()`, or just a component options object.
// We'll talk about nested routes later.
const routes = [
    { path: '/',
        // component: () => import('@/views/Home/Home'),
        name: 'dashboard',
        component: { template: '<landing-page></landing-page>' },
        meta: { title: "JavalinVue - Dashboard"}},
    { path: '/console',
        name: 'console',
        component: { template: '<console-messages></console-messages>' },
        meta: { title: "JavalinVue- Console"}},
    { path: '/cards',
        name: 'cards',
        component: { template: '<user-cards></user-cards>' },
        meta: { title: "JavalinVue - Cards"}},
    { path: '/users',
        name: 'users',
        component: { template: '<user-overview></user-overview>' },
        meta: { auth: true, title: "JavalinVue - Users"}},
    { path: "*",
        name: 'notfound',
        component: { template: '<not-found></not-found>' },
        meta: { title: "JavalinVue - 404"}},
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
export const router = new VueRouter({
    //mode: 'history',
    routes,
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        } else {
            return { x: 0, y: 0 };
        }
    }
})

Vue.use(VueRouter);
