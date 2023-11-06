import React, { useEffect, useRef, useState } from "react";
import "../../styles/modal.css";
import ImagePlaceHolder from "../shared/ImagePlaceHolder";
import ImageUpload from "../shared/ImageUpload";

const CategoryModal = ({ closeModal, data }) => {
  const [status, setStatus] = useState(-10);
  const [progress, setProgress] = useState(0);
  const fileInputRef = useRef(null);
  const [file, setFile] = useState();


  const handleFileChange = (event) => {
    const newFile = event.target.files[0];
    
    if (newFile) {
      // Handle the selected file here
      setFile(newFile);
      setProgress(0);
    }
  };

  const handleImageSelect = () => {
    fileInputRef.current.click();
  };

  useEffect(() => {
    const timer = setTimeout(() => {
      if (status <= 100) setStatus(status + 1);
    }, 20);
  });

  return (
    <div className="modal-container">
      <div className="modal-header">
        <div className="modal-title">Category</div>
        <div className="btn-close" onClick={closeModal}>
          X
        </div>
      </div>

      <div className="modal-body">
        <div className="left">
         <div className="figure" onClick={handleImageSelect}>
         {data.image || file ? (
            <div><img ref={fileInputRef}  src={URL.createObjectURL(file) || data.image} /></div>
          ) : (
            <div ref={fileInputRef}>
              <ImagePlaceHolder />
              
              
            </div>
          )}
         </div>

<input
              type="file"
              ref={fileInputRef}
              accept="image/*"
              style={{ display: "none" }}
              onChange={handleFileChange}
              
            />

         {status>=0 && status <=100 &&  <ImageUpload
            filename={"Test file. jpg"}
            size={"15MB"}
            precentage={status}
          />}
        </div>
        <div className="right-form">
        <div className="input-item">
              <div className="label">Category Name</div>
              <input type="text" placeholder="Category Name" className="field" />
            </div>

            <div className="input-item">
              <button className="btn">Submit</button>
            </div>
        </div>
      </div>
    </div>
  );
};

export default CategoryModal;
