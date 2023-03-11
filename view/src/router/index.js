import { createRouter, createWebHistory } from 'vue-router'

import MainPage from "@/components/MainPage.vue";
import CatalogPage from "@/components/CatalogPage.vue";
import Profile from "@/components/Profile.vue";
import Login from "@/components/LoginPage.vue";
import Register from "@/components/RegisterPage.vue";
import MyProducts from "@/components/MyProducts.vue";
import MyRecipes from "@/components/MyRecipes.vue";
import CreateRecipe from "@/components/CreateRecipe.vue";
import ConfirmRegister from "@/components/ConfirmRegisterPage.vue";
import SendLink from "@/components/SendLinkPage.vue"
import ResetPasswordPage from "@/components/ResetPasswordPage.vue"
import ResetPasswordEmail from "@/components/ResetPasswordEmail.vue"

import auth from "../service/auth"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "MainPage",
            component: MainPage,
        },
        {
            path: "/catalog",
            name: "CatalogPage",
            component: CatalogPage,
        },
        {
            path: "/profile",
            name: "Profile",
            component: Profile,
            beforeEnter: auth,
        },
        {
            path: "/auth/authenticate",
            name: "LoginPage",
            component: Login,
        },
        {
            path: "/auth/register",
            name: "RegisterPage",
            component: Register,
        },
        {
            path: "/my-products",
            name: "MyProducts",
            component: MyProducts,
        },
        {
            path: "/my-recipes",
            name: "MyRecipes",
            component: MyRecipes,
        },
        {
            path: "/create-recipe",
            name: "CreateRecipe",
            component: CreateRecipe,
        },
        {
            path: "/sendLink",
            name: "SendLink",
            component: SendLink,
        },
        {
            path: "/auth/confirmRegistration",
            params: { verificationToken: String },
            name: "ConfirmRegister",
            component: ConfirmRegister,
        },
        {
            path: "/auth/provideEmail",
            name: "ResetPasswordEmail",
            component: ResetPasswordEmail,
        },
        {
            path: "/auth/resetPassword",
            params: { resetToken: String },
            name: "ResetPasswordPage",
            component: ResetPasswordPage,
        }
    ],
})
export default router;