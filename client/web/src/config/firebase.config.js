import { initializeApp } from "firebase/app";
import "firebase/storage";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyC22TsKgriLirXFc3ohpa2fIF_zXIYDfn4",
  authDomain: "flashmart-c51b3.firebaseapp.com",
  projectId: "flashmart-c51b3",
  storageBucket: "flashmart-c51b3.appspot.com",
  messagingSenderId: "609204533242",
  appId: "1:609204533242:web:86fcbd48c05ad456151643",
  measurementId: "G-39CXQVYS0Y"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);

