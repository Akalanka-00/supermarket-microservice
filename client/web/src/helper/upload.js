import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";
import { storage } from '../config/firebase.config';

const onUpload = async (image, destination, setProgress, setDownloadLink) => {
    if (image) {
        const fileName = image.name
        const fileType = fileName.slice(fileName.lastIndexOf(".") + 1);
        const filePath = `${destination}/${fileName}.${fileType}`;

        const imageRef = ref(storage, filePath);
        const uploadTask = uploadBytesResumable(imageRef, image);

      uploadTask.on(
        "state_changed",
        (snapshot) => {
          // Handle upload progress here
          const progress =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          setProgress(progress);
          console.log(progress)
        },
        (error) => {
          // Handle error during upload
          console.error("Upload error:", error);
        },
        () => {
          // Upload completed successfully
          getDownloadURL(uploadTask.snapshot.ref)
            .then((url) => {
              setDownloadLink(url)
            })
            .catch((error) => {
              // Handle error while getting download URL
              alert("Error getting download URL:", error);
              console.log(error)
            });
        }
      );
    } else {
      alert("Img not valid");
    }
  };

  export default onUpload