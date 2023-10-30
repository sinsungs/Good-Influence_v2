import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

function PostReviewList() {
  const navigate = useNavigate();
  const jwtToken = useRecoilValue(tokenState);
  const [posts, setPosts] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    axios.get(`/postreview/list?page=${currentPage}`)
      .then(response => {
        setPosts(response.data.content);
        setTotalPages(response.data.totalPages);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }, [currentPage]);

  const deletePost = (prno) => {
    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }
    alert('추천게시글 삭제 하시겠습니까 ?');
    axios.delete(`/postreview/delete/${prno}`)
      .then(response => {
        console.log(response.data);
        alert(response.data);
        window.location.reload();
      })
      .catch(error => {
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

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
          <div className="board" style={{ width: '100%', height: '700px', border: '1px solid #ccc' }}>
            <ul>
              {posts.map((post) => (

                <li key={post.prno} style={{ width: '100%', height: '70px', border: '1px solid #ccc', display: 'flex', alignItems: 'center' }}>
                  <div className="board-list" style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
                  <Link to={`/reviewread/${post.prno}`}>
                    <div className="board-content">
                      <p>
                      <strong style={{ fontSize: '20px' }}>
                        {post.title}
                      </strong>
                      </p>
                      <p>
                        <strong>작성자 :</strong> {post.writer}
                      </p>
                    </div>
                    </Link>
                    <div className="board-actions">
                      <button
                        onClick={() => deletePost(post.prno)}
                        style={{ backgroundColor: 'red' }}
                      >
                        후기글 삭제
                      </button>
                    </div>
                  </div>
                </li>

              ))}
            </ul>
            <div>
              <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 0}>
                이전 페이지
              </button>
              <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage === totalPages - 1}>
                다음 페이지
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostReviewList;
