self.addEventListener("install", event => {
  console.log("Service Worker installé");
});

self.addEventListener("fetch", event => {
  // Mode pass-through : on ne met rien en cache pour l'instant
});
