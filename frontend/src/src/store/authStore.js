import { create } from "zustand";
import { persist } from "zustand/middleware";

export const useAuthStore = create(
  persist(
    (set) => ({
      user: null, // holds user info (e.g., username, email
      token: null, // holds the JWT string
      isAuthenticated: false,

        // ACTIONS (functiosn to change the state)

      // call this when login is successful
      login: (userData, token) =>
        set({
          user: userData,
          token: token,
          isAuthenticated: true,
        }),

        // call this to logoout
      logout: () =>
        set({
          user: null,
          token: null,
          isAuthenticated: false,
        }),
    }),
    {
        // the name of hte key in LocalStorage
      name: "auth-storage",
    }
  )
);
