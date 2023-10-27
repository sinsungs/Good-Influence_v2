import '../App.css';
import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';


function PostReviewList() {

  const navigate = useNavigate();

  // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  const [Posts, setPosts] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/postreview/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        setPosts(response.data);
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }, []);


  const deletePost = (prno) => {


    if (!jwtToken) {
      // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
      alert('로그인이 필요합니다.');
      return;
    }
    
    alert('추천게시글 삭제 하시겠습니까 ?')
  
    axios.delete(`/postreview/delete/${prno}`,
    {
      // headers: {
      //   Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
      // },
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

    navigate('/reviewpost');
    

  };

  return (
    <div className="App">
      <div className="back">
        <div className="container">
          <div className="search">
            <button className="login-button" onClick={handleRectangleClick}>
              후기 게시글 작성
            </button>
          </div>
          <div className="board" style={{width:'100%', height:'700px', border: '1px solid #ccc'}}>
            <ul>
              {Posts.map((Post) => (
                <li key={Post.prno} style={{width:'100%', height:'70px', border: '1px solid #ccc', display: 'flex', alignItems: 'center'}}>
                  <div className="board-list" style={{display: 'flex', justifyContent: 'space-between', width: '100%'}}>
                    <div className="board-content">
                      <p>
                        <strong style={{fontSize: '20px'}}>{Post.title}</strong> 
                      </p>
                      <p>
                        <strong>작성자 :</strong> {Post.writer}
                      </p>
                    </div>
                    <div className="board-actions">
                      <button
                        onClick={() => deletePost(Post.prno)}
                        style={{ backgroundColor: 'red' }}
                      >
                        후기글 삭제
                      </button>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostReviewList;
