import React, { useEffect, useState } from "react";
import "../../styles/modal.css";
import ImagePlaceHolder from "../shared/ImagePlaceHolder";
import ImageUpload from "../shared/ImageUpload";

const CategoryModal = ({ closeModal, data }) => {
  const [status, setStatus] = useState(-10);


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
          {data.image ? (
            <img src={data.image} />
          ) : (
            <div onClick={() => console.log("hehe")}>
              <ImagePlaceHolder />
            </div>
          )}
         {status>=0 && status <=100 &&  <ImageUpload
            filename={"Test file. jpg"}
            size={"15MB"}
            precentage={status}
          />}
        </div>
        <div className="right">
        <div className="input-item">
              <div className="label">Category Name</div>
              <input type="text" placeholder="Category Name" className="field" />
            </div>

            
        </div>
      </div>
    </div>
  );
};

export default CategoryModal;
