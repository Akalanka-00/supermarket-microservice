import React, { useEffect, useRef, useState } from "react";
import "../../styles/modal.css";
import ImagePlaceHolder from "../shared/ImagePlaceHolder";
import ImageUpload from "../shared/ImageUpload";
import onUpload from "../../helper/upload";
import { productItems } from "../../context/Products";

const ProductModal = ({ closeModal, data }) => {
  const [progress, setProgress] = useState(0);
  const fileInputRef = useRef(null);
  const [file, setFile] = useState();
  const [downloadLink, setDownloadLink] = useState(null);
  const { name, image, price, category } = data;
  const [selectedCategory, setSelectedCategory] = useState("");

  const handleFileChange = (event) => {
    const newFile = event.target.files[0];

    if (newFile) {
      // Handle the selected file here
      setFile(newFile);
      setProgress(0);
      onUpload(newFile, "/product", setProgress, setDownloadLink);
    }
  };

  const handleImageSelect = () => {
    fileInputRef.current.click();
  };

  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
  };

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
            {data.image || downloadLink ? (
              // <div><img ref={fileInputRef}  src={URL.createObjectURL(file) || data.image} /></div>
              <div>
                <img ref={fileInputRef} src={downloadLink || data.image} />
              </div>
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

          {progress > 0 && progress < 100 && (
            <ImageUpload
              filename={file.name}
              size={file.size}
              precentage={progress}
            />
          )}
        </div>
        <div className="right-form">
          <div className="input-item">
            <div className="label">Product Category</div>
            <select className="input-select" value={selectedCategory} onChange={handleCategoryChange}>
              <option className="input-select-option" value="">All Categories</option>
              {Array.from(
                new Set(productItems.map((item) => item.category)).values()
              ).map((category) => (
                <option key={category} value={category}>
                  Category {category}
                </option>
              ))}
            </select>
          </div>

          <div className="input-item">
            <div className="label">Product Name</div>
            <input type="text" placeholder="Product Name" className="field" />
          </div>

          <div className="input-item">
            <div className="label">Unit Price</div>
            <input type="text" placeholder="Product Name" className="field" />
          </div>

          <div className="input-item">
            <button className="btn">Submit</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductModal;
