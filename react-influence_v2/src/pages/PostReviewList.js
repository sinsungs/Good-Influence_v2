import '../App.css';
import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';


function PostReview() {

  const navigate = useNavigate();

  const jwtToken = useRecoilValue(tokenState); 

  const handleRectangleClick = () => {

  if (!jwtToken) {
    alert('로그인이 필요합니다.');
    return;
  }

  navigate('/reviewpost');
  
};


  return (
    <div className="App">
      <div className='back'>
      <div className="container">
        <div className="search">
              <button className="login-button" onClick={handleRectangleClick} >
                  후기 게시글 작성
              </button>
        </div>


      </div>
      </div>
    </div>
  );
}

export default PostReview;
