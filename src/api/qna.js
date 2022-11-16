import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleNo, success, fail) {
  api.get(`/qna/${articleNo}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleNo, success, fail) {
  api.delete(`/qna/${articleNo}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
