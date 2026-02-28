import api from "./api";

function Upload() {

  const uploadFile = async (e) => {
    const formData = new FormData();
    formData.append("file", e.target.files[0]);
    formData.append("userId", 1);

    await api.post("/api/uploads", formData);
    alert("Uploaded!");
  };

  return <input type="file" onChange={uploadFile} />;
}

export default Upload;