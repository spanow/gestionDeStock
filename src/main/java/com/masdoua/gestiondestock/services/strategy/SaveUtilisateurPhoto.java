package com.masdoua.gestiondestock.services.strategy;

import com.masdoua.gestiondestock.dto.UtilisateurDto;
import com.masdoua.gestiondestock.exception.ErrorCodes;
import com.masdoua.gestiondestock.exception.InvalidOperationException;
import com.masdoua.gestiondestock.services.FlickrService;
import com.masdoua.gestiondestock.services.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

  private FlickrService flickrService;
  private UtilisateurService utilisateurService;

  @Autowired
  public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
    this.flickrService = flickrService;
    this.utilisateurService = utilisateurService;
  }

  @Override
  public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    UtilisateurDto utilisateur = utilisateurService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    utilisateur.setPhoto(urlPhoto);
    return utilisateurService.save(utilisateur);
  }
}
