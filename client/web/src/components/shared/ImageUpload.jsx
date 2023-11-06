import React from 'react'
import {BsCardImage} from "react-icons/bs"
import "../../styles/uploadImage.css"

const ImageUpload = ({filename,size, precentage, onHide}) => {

    
  return (
    <div className='image-upload-container'>
      <div className="info">
        <div className="left">
            <BsCardImage className='icon'/>
        </div>
        <div className="right">
            <div className="name">{filename}</div>
            <div className="size">{size}</div>
        </div>
      </div>
      <div className="upload">
        <div className="status-bar">
        <div className="status-bar complete" style={{ backgroundColor: "#00B250", width:`${precentage}%` }}></div>
        </div>
        
      </div>
    </div>
  )
}

export default ImageUpload
