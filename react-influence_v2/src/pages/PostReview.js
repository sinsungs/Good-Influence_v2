import React, {useState, useEffect} from 'react';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';


function RecommendPost() {

  const navigate = useNavigate();

  // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  // 이미지 State
  const [file, setFile] = useState(null);
  const [imagePath, setImagePath] = useState('');

  const handleImageUpload = (event) => {
    const selectedImage = event.target.files[0];
    setFile(selectedImage);
  };


// 게시글 작성 Start
  const handleSubmit = (event) => {

    event.preventDefault();
  
    
    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }
    
    const title = event.target.elements.title.value;
    const content = event.target.elements.content.value;


    if (!title || !content || !file) {
      alert('모든 필수 입력란을 채워주세요.');
      return;
    }

    const postData = {
      title: title,
      content: content,
    };



    const formData  = new FormData();

    formData.append('dto', new Blob([JSON.stringify(postData)], { type: "application/json" }));
    formData.append('file', file);

    axios.post('/postreview/register', formData, {
      headers: {
        Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
      },
    })
      .then(response => {

        console.log('Server response:', response.data);

        alert('추천글 작성을 완료했습니다.', response.data);
        navigate('/reviewlist');

        // window.location.href = '/list';

      })
      .catch(error => {

        console.error('Error:', error);

      });
  };
// 게시글 작성 End

  return (
    
    <div className="App">
      <div className='back'>
      <div className="container">

        <div style={{ width: '100%'}}>

          <h1>소셜 모임의 후기를 작성해 주세요</h1>
          

          <form onSubmit={handleSubmit}>
              <div>
                <label htmlFor="image">배경 이미지:</label>
                <input
                  type="file"
                  id="image"
                  accept="image/*" // 이미지 파일만 업로드 가능하도록 설정
                  onChange={handleImageUpload}
                />
              </div>

              <label>게시글 제목</label>
              <input type="text" className="custom-input" name="title" placeholder="제목을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <label>추천 내용</label>
              <input type="text" className="custom-input" name="content" placeholder="내용을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <button type="submit">작성하기</button>
          </form>

        </div>
      </div>
    </div>
    </div>


    
  );
}

export default RecommendPost;
