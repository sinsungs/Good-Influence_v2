import '../App.css';
import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';


function RecommendList() {

  const navigate = useNavigate();

  // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  const [Influencer, setInfluencer] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/api/post/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        setInfluencer(response.data);
        console.log(response.data);
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }, []);


  const deletePost = (ino, pno) => {


    if (!jwtToken) {
      // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
      alert('로그인이 필요합니다.');
      return;
    }
    
    alert('추천게시글 삭제 하시겠습니까 ?')
  
    axios.delete(`/api/post/delete/${ino}/${pno}`,
    {
      headers: {
        Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
      },
    })

      .then(response => {
        // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
        console.log(response.data);
        alert(response.data);
        window.location.reload();
        // handleModalClose();
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });

  }

  const handleRectangleClick = () => {

    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }

    navigate('/post');
    

  };

  return (
    <div className="App">
      <div className='back'>
      <div className="container">
      <div className="search">
            <button className="login-button" onClick={handleRectangleClick}>
                추천 게시글 작성
            </button>
        </div>
        {Influencer.map(Influencer => (
          <div className="box" key={Influencer.ino}>
            <div className="box-header">
            
              <img src={Influencer.imageurl} alt="image" style={{ width: '100%', height: '150px' }}/>
              
            </div>
            <div className="box-body">

              제목 : {Influencer.title}<br/>
              내용 : {Influencer.content}<br/>

              <div className="y-box-header">
                <img src={Influencer.influencerimage} alt="image" style={{ width: '30%', height: '50%' }}/>
                추천 인플루언서 : {Influencer.name}
                <button onClick={() => deletePost(Influencer.ino, Influencer.pno)} style={{backgroundColor:"red"}}>추천글 삭제</button>
              </div>
              
              <div>
              
              </div>

              
            </div>
            <div className="box-footer">
              <a>
                <span className='box-name'>작성자 : {Influencer.writer} </span>
              </a>
              <span className='box-like'>
                  Like : 0
              </span>
            </div>
           
            {/* <button>추천글 삭제하기</button> */}
          </div>
        ))}

      </div>

      </div>
    </div>
  );
}

export default RecommendList;
