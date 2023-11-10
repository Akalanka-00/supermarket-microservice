import React from 'react'
import {BsCardImage} from "react-icons/bs"
import "../../styles/uploadImage.css"

const ImageUpload = ({filename,size, precentage}) => {
const fileSize = `${(size / 1024 / 1024).toFixed(2)} MB`
    
  return (
    <div className='image-upload-container'>
      <div className="info">
        <div className="image-upload-left">
            <BsCardImage className='icon'/>
        </div>
        <div className="image-upload-right">
            <div className="name">{filename}</div>
            <div className="size">{fileSize}</div>
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
