import { defineConfig } from 'vite'
import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'
import { VitePWA } from 'vite-plugin-pwa'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react(),
    tailwindcss(),
    VitePWA({
      registerType: 'autoUpdate',
      includeAssets: ['vite.svg'],
      manifest: {
        name: 'EmulaTV Remote',
        short_name: 'Remote',
        description: 'Télécommande pour EmulaTV',
        theme_color: '#000000',
        background_color: '#ffffff',
        display: 'standalone',
        start_url: '/remote/',
        icons: [
          {
            src: '/remote/icons/AppImages/android/android-launchericon-192-192.png',
            sizes: '192x192',
            type: 'image/png'
          },
          {
            src: '/remote/icons/AppImages/android/android-launchericon-512-512.png',
            sizes: '512x512',
            type: 'image/png'
          },
          {
            src: '/remote/icons/AppImages/android/android-launchericon-512-512.png',
            sizes: '512x512',
            type: 'image/png',
            purpose: 'any maskable'
          }
        ]
      }
    })
  ],
  base: '/remote/',
})
